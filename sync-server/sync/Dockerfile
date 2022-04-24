FROM node:17.4.0-alpine3.15
WORKDIR /src
COPY package.json .
RUN npm install
RUN npm install -g nodemon
RUN chown -R 1000:1000 "/root/.npm"