FROM openjdk:14
WORKDIR /usr/app
COPY . .
EXPOSE 8080
WORKDIR /usr/app/constructionyard
RUN chmod +x /usr/app/constructionyard/gradlew
CMD [ "/usr/app/constructionyard/gradlew", "bootRun" ]