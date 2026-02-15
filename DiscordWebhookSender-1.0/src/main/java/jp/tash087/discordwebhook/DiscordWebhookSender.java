package jp.tash087.discordwebhook;

import okhttp3.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import java.io.IOException;

public class DiscordWebhookSender extends JavaPlugin implements CommandExecutor {

    private final OkHttpClient client = new OkHttpClient();

    @Override
    public void onEnable() {
        saveDefaultConfig();
        getCommand("dw").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) return false;

        String message;
        if (args[0].equalsIgnoreCase("default")) {
            // configから定型文を取得
            message = getConfig().getString("default-message", "No message set.");
        } else {
            // コマンド引数をすべて結合してメッセージにする
            message = String.join(" ", args);
        }

        sendToDiscord(message);
        sender.sendMessage("§aDiscordに送信しました: " + message);
        return true;
    }

    private void sendToDiscord(String content) {
        String url = getConfig().getString("webhook-url");
        if (url == null || url.isEmpty()) return;

        // Discord WebhookのJSON形式を作成
        String json = "{\"content\": \"" + content + "\", \"username\": \"" + getConfig().getString("bot-name") + "\"}";

        RequestBody body = RequestBody.create(json, MediaType.parse("application/json; charset=utf-8"));
        Request request = new Request.Builder().url(url).post(body).build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                getLogger().severe("Webhook送信失敗: " + e.getMessage());
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                response.close();
            }
        });
    }
}