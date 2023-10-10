const { Discord, Client, Collection, Intents } = require('discord.js');
const mongoose = require('mongoose');
require("dotenv").config();

//Initialize the client
const client = new Client({ intents: 32767 });
module.exports = client;

//Variables
client.commands = new Collection();
client.slashCommands = new Collection();

//Initialize FS
require("./handler")(client);

//Login to the Client
client.login(process.env.TOKEN);

// Bot is NOT complete
