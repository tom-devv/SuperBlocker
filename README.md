# SuperBlocker

SuperBlocker is a simple command blocker, it can block any command. It has stops players from using "tab-completion" maliciously and will only send auto-completion results if players are involved

## Dependencies

Plugin is heavily dependent on [ProtocolLib](https://github.com/dmulloy2/ProtocolLib/), which is used to intercept packets sent by players

## Usage

Inside of `config.yml` you can add commands to block under `blockedCommands`

When `discreteBlock` is enabled ; blocked commands will return `Unknown command. Type "/help" for help."`, the idea behind this is it is harder for people to gauge what plugins the server has loaded, **knowing what plugins are on the server is the first step in finding exploits**, this aims to help that problem.

## Permissions
**Any** player with the `block.bypass` permission will be ignored **completely** by the plugin.
