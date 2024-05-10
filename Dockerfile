# Use Alpine Linux as the base image
FROM alpine AS builder

# Set up working directory
WORKDIR /opt/hivemq
# Download and extract HiveMQ Edge
RUN wget -qO- https://www.hivemq.com/releases/hivemq-edge-latest.zip | unzip -
# Runtime stage


FROM openjdk:latest
# Set up working directory and copy HiveMQ Edge from the builder stage
WORKDIR /opt/hivemq
COPY --from=builder /opt/hivemq .
RUN chmod +x -R ./
# Expose MQTT and WebSocket ports
EXPOSE 1883 8000
RUN microdnf install findutils
COPY . .
# Start HiveMQ Edge
ENTRYPOINT ["/bin/bash", "lib/runtests.sh"]
