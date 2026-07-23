# Change Log

## 2.0.0-alpha.2 (July 23, 2026)

Update internal libraries (security fixes)

## 2.0.0-alpha.1 (May 13, 2025)

Rewrite in Rust.

### BREAKING CHANGES

- **Dice** with space in `+` (like `/d4 + 1`) modifiers non-supported.
- **Dice**: with bot name non-supported, ex. present in help.
- `TELEGRAM_BOT_NAME` is obsolete.

## 1.2 (May 25, 2020)

Fix the wrong TSL connection to API

## 1.1 (November 18, 2016)

First stable release

#### With **Dice** improves
- Result returns faster
- New alias "**m**" (like d20**m4**) for modifiers

### Bug fixing
- **Dice** – default command "/dice" fixed
- **Help** – remove a newline from the message bottom

### Development features
- `travis` – builds shared cache
- **All plugins with unit tests now**

## 1.0 (October 19, 2016)

Initial public release

#### Three new features

- **Dice** — command for generate a dice result
- **8-ball** — command analog [Magic 8-ball](https://en.wikipedia.org/wiki/Magic_8-Ball)
- **Help** — command show commands list

### Preferences (from ENV)
- `TELEGRAM_BOT_NAME` — registered bot name
- `BOT_TELEGRAM_TOKEN` — token to access for HTTP API
