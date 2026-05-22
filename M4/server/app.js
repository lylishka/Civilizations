const express = require('express');
const fs = require('fs');
const path = require('path');
const hbs = require('hbs');
const MySQL = require('./utilsMySQL');

const app = express();
const port = 3000;

// Detectar si estem al Proxmox (si és pm2)
const isProxmox = !!process.env.PM2_HOME;

// Iniciar connexió MySQL
const db = new MySQL();
if (!isProxmox) {
  db.init({
    host: '127.0.0.1',
    port: 3306,
    user: 'root',
    password: 'root',
    database: 'civilizations',
  });
} else {
  db.init({
    host: '127.0.0.1',
    port: 3306,
    user: 'super',
    password: '1234',
    database: 'civilizations'
  });
}

// Static files - ONLY ONCE
app.use(express.static('public'))
app.use(express.urlencoded({ extended: true }))

// Disable cache
app.use((req, res, next) => {
  res.setHeader('Cache-Control', 'no-store, no-cache, must-revalidate, proxy-revalidate');
  res.setHeader('Pragma', 'no-cache');
  res.setHeader('Expires', '0');
  res.setHeader('Surrogate-Control', 'no-store');
  next();
});

// Handlebars
app.set('views', path.join(__dirname, 'views'));
app.set('view engine', 'hbs');
app.locals.layout = 'layouts/main';

// Registrar "Helpers .hbs" aquí
hbs.registerHelper('eq', (a, b) => a == b);
hbs.registerHelper('gt', (a, b) => a > b);

// Partials de Handlebars
hbs.registerPartials(path.join(__dirname, 'views', 'partials'));


/*
===================
     MAIN ROUTE
===================
*/
app.get('/', async (req, res) => {
  try {

    // Obtenir les dades de la base de dades
    const ultimesBatallesRows = await db.query(`
        SELECT bs.num_battle, DATE_FORMAT(bs.battle_date, '%d/%m/%Y %H:%i') AS fecha, cs.name, bl.winner
        FROM battle_stats bs
          JOIN civilizacion_stats cs ON bs.civilization_id = cs.civilization_id
          JOIN battle_log bl ON bs.num_battle = bl.num_battle
        ORDER BY battle_date DESC
        LIMIT 2
    `);

    // Transformar les dades a JSON (per les plantilles .hbs)
    // Cal informar de les columnes i els seus tipus
    const ultimesBatallesJson = db.table_to_json(ultimesBatallesRows, { 
      num_battle: "number",
      name: "string",
      winner: "string",
      fecha: "string"
    });


    // Llegir l'arxiu .json amb dades comunes per a totes les pàgines
    const commonData = JSON.parse(
      fs.readFileSync(path.join(__dirname, 'data', 'common.json'), 'utf8')
    );

    // Construir l'objecte de dades per a la plantilla
    const data = {
      titul: "Principal",
      ultimesBatalles: ultimesBatallesJson,
      common: commonData
    };

    // Renderitzar la plantilla amb les dades
    res.render('principal', data);
  } catch (err) {
    console.error(err);
    res.status(500).send('Error consultant la base de dades');
  }
});


/*
=======================
     BATALLES ROUTE
=======================
*/
app.get('/batalles', async (req, res) => {
  try {

    // Obtenir les dades de la base de dades
    const totalRows = await db.query(`
      SELECT COUNT(*) AS total
      FROM battle_stats
    `);

    const llistatRows = await db.query(`
      SELECT bs.*, DATE_FORMAT(bs.battle_date, '%d/%m/%Y %H:%i') AS fecha, cs.name, bl.winner
      FROM battle_stats bs
        JOIN civilizacion_stats cs ON bs.civilization_id = cs.civilization_id
        JOIN battle_log bl ON bs.num_battle = bl.num_battle
      ORDER BY battle_date DESC
    `);

    // Transformar les dades a JSON (per les plantilles .hbs)
    const totalJson = db.table_to_json(totalRows, {
      total: 'number'
    });

    const llistatJson = db.table_to_json(llistatRows, {
      num_battle: "number",
      civilization_id: "number",
      name: "string",
      wood_acquired: "number",
      iron_acquired: "number",
      winner: "string",
      fecha: 'string'
    });

    // Llegir l'arxiu .json amb dades comunes per a totes les pàgines
    const commonData = JSON.parse(
      fs.readFileSync(path.join(__dirname, 'data', 'common.json'), 'utf8')
    );

    // Construir l'objecte de dades per a la plantilla
    const data = {
      titul: "Batalles",
      total: totalJson[0],
      llistat: llistatJson,
      common: commonData
    };

    // Renderitzar la plantilla amb les dades
    res.render('batalles', data);
  } catch (err) {
    console.error(err);
    res.status(500).send('Error consultant la base de dades');
  }
});



/*
=====================
    INFORMES ROUTE
=====================
*/
app.get('/informesbatalles', async (req, res) => {
  try {
    
    const id = parseInt(req.query.id);

    if (!Number.isInteger(id) || id <= 0) {
      return res.status(400).send('Paràmetre id invàlid')
    }

    // Obtenir les dades de la base de dades
    const datosRows = await db.query(`
      SELECT bs.*, DATE_FORMAT(bs.battle_date, '%d/%m/%Y %H:%i') AS fecha, bl.log_entry, cs.name, cs.food_amount, cs.mana_amount
      FROM battle_stats bs
        LEFT JOIN battle_log bl ON bs.num_battle = bl.num_battle
        JOIN civilizacion_stats cs ON bs.civilization_id = cs.civilization_id
      WHERE bs.num_battle = ${id};
    `);

    // Transformar les dades a JSON (per les plantilles .hbs)
    const datosJson = db.table_to_json(datosRows, {
      num_battle: "number",
      name: "string",
      wood_acquired: "number",
      iron_acquired: "number",
      food_amount: "string",
      mana_amount: "string",
      fecha: 'string',
      log_entry: 'string'
    });

    // Llegir l'arxiu .json amb dades comunes per a totes les pàgines
    const commonData = JSON.parse(
      fs.readFileSync(path.join(__dirname, 'data', 'common.json'), 'utf8')
    );

    // Construir l'objecte de dades per a la plantilla
    const data = {
      titul: "Informe de Battalla",
      datos: datosJson[0],
      common: commonData
    };

    // Renderitzar la plantilla amb les dades
    res.render('informes', data);
  } catch (err) {
    console.error(err);
    res.status(500).send('Error consultant la base de dades');
  }
});



/*
=========================
    CIVILITZACIO ROUTE
=========================
*/
app.get('/civilitzacio', async (req, res) => {
  try {

    // Obtenir les dades de la base de dades
    const statsRows = await db.query(`
      SELECT *
      FROM civilizacion_stats
      LIMIT 1
    `)  


    // Transformar les dades a JSON (per les plantilles .hbs)
    const statsJson = db.table_to_json(statsRows, {
      civilization_id: 'number', 
      name: 'string', 
      wood_amount: 'number',
      iron_amount: 'number',
      food_amount: 'number',
      mana_amount: 'number',
      magicTower_counter: 'number',
      church_counter: 'number',
      farm_counter: 'number',
      smithy_counter: 'number',
      carpentry_counter: 'number',
      technology_defense_level: 'number',
      technology_attack_level: 'number',
      battles_counter: 'number',
    });

    // Llegir l'arxiu .json amb dades comunes per a totes les pàgines
    const commonData = JSON.parse(
      fs.readFileSync(path.join(__dirname, 'data', 'common.json'), 'utf8')
    );

    // Construir l'objecte de dades per a la plantilla
    const data = {
      titul: "Civilizacions",
      stats: statsJson[0],
      common: commonData
    };

    // Renderitzar la plantilla amb les dades
    res.render('civilitzacio', data);
  } catch (err) {
    console.error(err);
    res.status(500).send('Error consultant la base de dades');
  }
});

app.get('/programadors', async (req, res) => {
  try {

    // Llegir l'arxiu .json amb dades comunes per a totes les pàgines
    const commonData = JSON.parse(
      fs.readFileSync(path.join(__dirname, 'data', 'common.json'), 'utf8')
    );

    // Construir l'objecte de dades per a la plantilla
    const data = {
      titul: "Programadors",
      common: commonData
    };

    // Renderitzar la plantilla amb les dades
    res.render('programadors', data);
  } catch (err) {
    console.error(err);
    res.status(500).send('Error consultant la base de dades');
  }
});

// Start server
const httpServer = app.listen(port, () => {
  console.log(`http://localhost:${port}`);
  console.log(`http://localhost:${port}/batalles`);
  console.log(`http://localhost:${port}/civilitzacio`);
  console.log(`http://localhost:${port}/programadors`);
});

// Graceful shutdown
process.on('SIGINT', async () => {
  await db.end();
  httpServer.close();
  process.exit(0);
});
