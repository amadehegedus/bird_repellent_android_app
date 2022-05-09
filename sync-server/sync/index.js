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

app.get("/sync/:id", async (req, res) => {
  console.log("get");
  const response = await redisClient.get(req.params.id);
  if (!response) {
    res.json([]);
    res.end();
  } else {
    res.json(JSON.parse(response));
    res.end();
  }
});

app.put("/sync/:id", async (req, res) => {
  console.log("put");
  await redisClient.set(req.params.id, JSON.stringify(req.body));
  res.json(req.body);
  res.end();
});

app.delete("/sync/:id", async (req, res) => {
  console.log("del");
  await redisClient.del(req.params.id);
  res.end();
});

redisClient.connect().then(
  app.listen(port, () => {
    console.log(`sync-server istening on port ${port}`);
  })
);
