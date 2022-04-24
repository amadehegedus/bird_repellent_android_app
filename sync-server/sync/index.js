const redis = require("redis");
const express = require("express");
const app = express();

const port = process.env.PORT || 3000;
const redisUrl = `redis://${process.env.REDIS_HOST || "redis"}`;
const redisPort = Number(process.env.REDIS_PORT || "6379");

const redisClient = redis.createClient({
  url: redisUrl,
  port: redisPort,
});

app.use(express.json());

app.get("/sync", async (req, res) => {
  //TODO
});

app.post("/sync", async (req, res) => {
  //Already exists
  if ((await redisClient.get(req.body.requestId)) != null) {
    res.status(400);
    res.json({ message: "Config already exists!" });
    return;
  }
  //Create
  redisClient.set(req.body.requestId, JSON.stringify(req.body));
  const response = req.body;
  delete response.adminId;
  res.status(200);
  res.json(response);
});

app.put("/sync", async (req, res) => {
  const configFound = JSON.parse(await redisClient.get(req.body.requestId));
  //Check adminId
  if (configFound != null && configFound.adminId != req.body.adminId) {
    res.status(400);
    res.json({ message: "Invalid adminId!" });
    return;
  }
  //Update (or Create)
  redisClient.set(req.body.requestId, JSON.stringify(req.body));
  const response = req.body;
  delete response.adminId;
  res.status(200);
  res.json(response);
});

app.delete("/sync", async (req, res) => {
  //TODO
});

redisClient.connect().then(
  app.listen(port, () => {
    console.log(`sync-server istening on port ${port}`);
  })
);
