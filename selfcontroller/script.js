const ws = new WebSocket("ws://127.0.0.1:15023");
var availableEffects;

ws.onmessage = (msg) => {
    ws.onmessage = () => {};
    ws.onclose = () => {
        window.location.reload();
    }
    availableEffects = msg.data.split(",");

    var i = 0;
    for (effect of availableEffects) {
        var button = document.createElement("button");
        button.innerText = effect;
        console.log(effect);
        button.onclick = (e) => {
            ws.send("activate " + e.target.innerText);
        };
        document.body.appendChild(button);
        if(i == 3) {
            i = 0;
            document.body.appendChild(document.createElement("br"));
        }
    }
};