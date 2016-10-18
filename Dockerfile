FROM ibmjava:latest

MAINTAINER Konstantin Chernenko <kb.chernenko@gmail.com> (@zoobestik)

ADD target/borken_bot-1.0-jar-with-dependencies.jar /app.jar

ENTRYPOINT exec java $JAVA_OPTS -Xmx96m -jar /app.jar
