# Java Roll Dice Telegram Bot

This is a Telegram bot that simulates dice rolls for users. Processed requests are saved in a database, and you can view statistics by visiting the provided HTTP endpoint.

## Deployed example

- https://t.me/JavaRollDiceBot - Telegram bot
- http://185.117.153.156:8081/stats - Statistics

## Stack

Spring boot, H2 database, Thymeleaf

## How to deploy

For deployment in the project, you need to add an application-secret.properties file in which you should specify the parameters telegram.bot.name and telegram.bot.token. You can obtain these parameters from https://t.me/BotFather.

Everything else is ready for building using Maven's package command.

<p align="center">
  <img src="java_roll_dice_bot.png" alt="Bot Image">
</p>