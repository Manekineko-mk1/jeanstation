## Stage 1
#FROM node:14.17.0-alpine as build-step
#RUN mkdir -p /app
#WORKDIR /app
#COPY package.json /app
##COPY package.json /app/package.json
#RUN npm install
##RUN npm install -g @angular/cli@7.3.9
#COPY . /app
#
##RUN npm run build --prod
##CMD ng serve --host 0.0.0.0
#CMD npm run start
#
### Stage 2
##FROM nginx:1.20.1
###COPY --from=build-step /app/docs /usr/share/nginx/html
##
##COPY --from=build-step /app/dist/ng-docker /usr/share/nginx/html
#EXPOSE 4200

FROM nginx:1.17.1-alpine as build-step
WORKDIR /app
COPY package.json ./
# install curl
RUN apk add --update curl
# install nodejs
RUN apk add --update nodejs npm
RUN npm install
COPY . .
EXPOSE 4200
CMD npm run start
