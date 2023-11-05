FROM node:21 AS build

WORKDIR /app

COPY package.json ./
COPY package-lock.json ./
RUN npm install
COPY . ./
RUN npm run build

FROM nginx:1.19-alpine AS deploy-static
RUN rm -rf /usr/share/nginx/html/*
COPY --from=build /app/build /usr/share/nginx/html
ENTRYPOINT [ "nginx", "-g", "daemon off;" ]