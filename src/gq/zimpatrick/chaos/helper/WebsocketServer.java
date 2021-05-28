package gq.zimpatrick.chaos.helper;

import gq.zimpatrick.chaos.Main;
import org.bukkit.Bukkit;
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import java.net.InetSocketAddress;
import java.util.Arrays;
import java.util.LinkedList;

public class WebsocketServer extends WebSocketServer {

    public WebsocketServer(int port) {
        super(new InetSocketAddress(port));
    }

    @Override
    public void onOpen(WebSocket webSocket, ClientHandshake clientHandshake) {
        StringBuilder available = new StringBuilder();
        for(ChaosEffect effect : Main.instance.effectManager.availableEffects) {
            available.append(effect.name).append(",");
        }
        webSocket.send(available.toString());
        System.out.println(webSocket.getRemoteSocketAddress().getAddress().getHostAddress() + " connected");
    }

    @Override
    public void onClose(WebSocket webSocket, int i, String s, boolean b) {
        System.out.println(webSocket.getRemoteSocketAddress().getAddress().getHostAddress() + " disconnected");
    }

    @Override
    public void onMessage(WebSocket webSocket, String s) {
        LinkedList<String> args = new LinkedList<>(Arrays.asList(s.split(" ")));
        String cmd = args.removeFirst();
        switch(cmd) {
            case "activate": {
                Bukkit.getScheduler().runTaskLater(Main.instance, new Runnable() {
                    @Override
                    public void run() {
                        System.out.println(s.replace("activate ", ""));
                        Main.instance.effectManager.enableEffect(Main.instance.effectManager.getEffectByName(s.replace("activate ", "")));
                    }
                }, 1L);
            }
        }

    }

    @Override
    public void onError(WebSocket webSocket, Exception e) {

    }

    @Override
    public void onStart() {
        System.out.println("Websocket server started");
    }
}
