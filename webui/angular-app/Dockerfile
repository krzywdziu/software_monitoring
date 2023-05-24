FROM node:latest as BUILDER
WORKDIR /app

COPY . .

RUN npm install
RUN npm run build


FROM nginx:alpine
COPY --from=BUILDER /app/dist/angular-app /usr/share/nginx/html
