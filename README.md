# telegram_bot_example

## Getting started
### Installing from command line[1]:
```bash
curl https://raw.githubusercontent.com/zoobestik/telegram_bot_example/master/docker-compose.yml > docker-compose.yml
echo "TELEGRAM_BOT_NAME=<...name...>" >> .env
echo "TELEGRAM_BOT_TOKEN=<...token...>" >> .env
```

[1]: Placeholder should be replaced by:
* `<...name...>` – registered bot name;
* `<...token...>` – token to access for HTTP API;

More information in [Telegram Bot Api page](https://core.telegram.org/bots#3-how-do-i-create-a-bot).
 
### Start a bot server instance
Starting a server instance is simple: ```docker-compose up```
