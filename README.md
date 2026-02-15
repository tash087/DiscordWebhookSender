# DiscordWebhookSender
Minecraft plugin. Sends messages to Discord via webhooks.


## 🌟 Features
- **Instant Messaging**: Send messages directly from the in-game chat to a Discord channel.
- **Preset Messages**: Quickly send a pre-configured "default" message from the config.
- **Asynchronous Execution**: Utilizes `OkHttp3`'s asynchronous calls to ensure zero impact on server performance (no main-thread blocking).
- **Customizable**: Easy configuration for Webhook URLs and Bot display names.

---

## ⚙️ Configuration
The `config.yml` allows you to define your Webhook credentials and default text.

```yaml
# ==========================================
# DiscordWebhookSender Config by tash087
# ==========================================

webhook-url: "https://discord.com/api/webhooks/..."
default-message: "サーバーからのお知らせ：現在メンテナンスの予定はありません。"
bot-name: "Server-Bot"

```

---

## 🚀 Commands & Permissions

* **Command**: `/dw <message | default>`
* **Description**: Sends the specified text or the default config message to Discord.
* **Permission**: Defaulted to all players (It is recommended to restrict this in `plugin.yml`).

---

## 🛠️ Technical Details

* **HTTP Library**: [OkHttp](https://square.github.io/okhttp/)
* **Process**:
1. Captures command arguments and joins them into a single string.
2. Constructs a JSON payload including `content` and `username`.
3. Executes an asynchronous POST request to the Discord API.
4. Logs a severe error in the console if the transmission fails.



---

## 📄 License

This project is licensed under the MIT License. See the LICENSE file for details.

Copyright (c) 2026 tash087

---

Developed by **tash087**


```
