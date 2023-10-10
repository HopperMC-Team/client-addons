const { Discord, Client, Collection, Intents } = require('discord.js');
const mongoose = require('mongoose');
require("dotenv").config();

//Initialize the client
const client = new Client({ intents: 32767 });
module.exports = client;

//Variables
client.commands = new Collection();
client.slashCommands = new Collection();

//Initialize the Database
mongoose.connect(process.env.mongodb, { useNewUrlParser: true, useUnifiedTopology: true }).then(console.log('Connected to Mongodb.'));

//Initialize FS
require("./handler")(client);

//Initialize Canvas


//Login to the Client
client.login(process.env.TOKEN);

// Not complete
