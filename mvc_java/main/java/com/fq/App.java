package com.fq;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.modelcontextprotocol.server.McpServer;
import io.modelcontextprotocol.server.McpServerFeatures;
import io.modelcontextprotocol.server.McpSyncServer;
import io.modelcontextprotocol.server.transport.StdioServerTransportProvider;
import io.modelcontextprotocol.spec.McpSchema;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App {
    public static void main(String[] args) {

        // Sync tool specification
        var schema = """
                {
                  "type" : "object",
                  "id" : "urn:jsonschema:Operation",
                  "properties" : {
                    "operation" : {
                      "type" : "string"
                    },
                    "a" : {
                      "type" : "number"
                    },
                    "b" : {
                      "type" : "number"
                    }
                  }
                }
                """;
        var syncToolSpecification = new McpServerFeatures.SyncToolSpecification(
                new McpSchema.Tool("calculator", "Basic calculator", schema),
                (exchange, arguments) -> {
                    // Tool implementation
                    String result = "calculator call ok";
                    return new McpSchema.CallToolResult(result, false);
                }
        );

        StdioServerTransportProvider transportProvider = new StdioServerTransportProvider(new ObjectMapper());
        // Create a server with custom configuration
        McpSyncServer syncServer = McpServer.sync(transportProvider)
                .serverInfo("my-server", "1.0.0")
                .capabilities(McpSchema.ServerCapabilities.builder()
                        .resources(false, true)  // Enable resource support
                        .tools(true)             // Enable tool support
                        .prompts(true)           // Enable prompt support
                        .logging()               // Enable logging support
                        .completions()           // Enable completions support
                        .build())
                .build();

// Register tools, resources, and prompts
        syncServer.addTool(syncToolSpecification);
//        syncServer.addResource(syncResourceSpecification);
//        syncServer.addPrompt(syncPromptSpecification);

// Close the server when done
//        syncServer.close();
    }
}