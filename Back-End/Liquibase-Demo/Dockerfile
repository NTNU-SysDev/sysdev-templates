FROM openjdk:8

# Create directory /app
RUN mkdir /app
WORKDIR /app

# We will mount our target directory there
VOLUME /app

# Expose Spring boot port 8080 to "the outside world"
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "lbdemo.jar"]