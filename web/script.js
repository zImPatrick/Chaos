var availableEffects = [];
var effectsToPick = [{}];
var hasVotedThisRound = [];
var beginDate = Date.now();
var totalVotes = 0;

(async () => {
    console.debug("Connecting to server");
    const ws = new WebSocket("ws://127.0.0.1:15023");

    ws.onerror = ws.onclose = () => {
    	window.location.reload();
    }

    ws.onmessage = (msg) => {
        ws.onmessage = () => {};
        availableEffects = msg.data.split(",");
        console.log(availableEffects)

        createListToPick();
        console.log(effectsToPick);
    };

    const newRound = () => {
        beginDate = Date.now();
        effectsToPick.sort((a, b) => a.picks - b.picks);
        totalVotes = 0;

        activateEffect(effectsToPick.reverse()[0].name)

        createListToPick();
        console.log(effectsToPick);
        hasVotedThisRound = [];
        console.log("new round");
    }

    const createListToPick = () => {
        effectsToPick =
            getRandom(availableEffects, 3).map(effect => { return { name: effect, picks: 0 } })

        updateChoices();
    }
    const updateChoices = () => {
        document.querySelector("#choices").innerHTML = 
    `<b>Nächste Runde in ${Math.round(30 - ((Date.now() - beginDate) / 1000))}</b><table><tr><th>ID</th><th>Effekt</th><th>Votes</th><th>%</th> ${effectsToPick.map((effect, index) => `<tr><td>${index+1}</td> <td>${effect.name}</td><td>${effect.picks}</td> <td>${(() => { var percent = (100 * effect.picks) / totalVotes; if(isNaN(percent)) percent = "0"; return Math.round(percent);})()}%</td></tr>`).join("")}`
    };

    const activateEffect = (effectName) => {
        ws.send("activate " + effectName);
        console.log("activated " + effectName);
    };

    console.debug("Connecting to Twitch...")
    const tmiClient = new tmi.Client({
        channels: ["zimpatrick"],
        connection: { reconnect: true }
    });
    await tmiClient.connect();

    tmiClient.on('message', (channel, tags, message, self) => {
        console.log(`${tags['display-name']}: ${message}`);
        if(!isNaN(parseInt(message)) && !hasVotedThisRound.includes(tags['display-name'])) {
            hasVotedThisRound.push(tags['display-name']);
            try {
                console.log(message);
                totalVotes++;
                effectsToPick[parseInt(message)-1].picks++
            } catch (e) {

            }
            updateChoices();
        }
    });

    beginDate = Date.now();
    setInterval(newRound, 30e3);
    setInterval(updateChoices, 1e3);

    


})();

function getRandom(arr, n) {
    var result = new Array(n),
        len = arr.length,
        taken = new Array(len);
    if (n > len)
        throw new RangeError("getRandom: more elements taken than available");
    while (n--) {
        var x = Math.floor(Math.random() * len);
        result[n] = arr[x in taken ? taken[x] : x];
        taken[x] = --len in taken ? taken[len] : len;
    }
    return result;
}