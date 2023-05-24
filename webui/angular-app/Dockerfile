FROM node:latest as BUILDER
WORKDIR /app

COPY . .

RUN npm install
RUN npm run build


FROM nginx:alpine
COPY ./nginx.conf /etc/nginx/conf.d/default.conf
COPY --from=BUILDER /app/dist/angular-app /usr/share/nginx/html
