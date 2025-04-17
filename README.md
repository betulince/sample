## Issues

In this project, we encountered a serialization issue while working with Micronaut's JsonMapper for handling complex data types like Map<String, Any>, specifically when wrapping it inside the Parameters class.

1. The @JsonAnyGetter annotation, which is typically used to flatten a map into the parent JSON object during serialization, does not behave as expected in this case. When used on a property of type Map<String, Any>, it leads to a Specific serializer required error.

2. When we do not add the @JsonAnyGetter annotation in the Parameters class, we do not face serialization errors. However, this results in an unwanted additional layer in the response JSON. Instead of having the parameters serialized directly as a simple map, the parameters are wrapped in an additional parameterValues field:
    
    ```json
    {
      "parameters": {
        "parameterValues": {
          "key1": "value1",
          "key2": "value2"
        }
      }
    }
    ```

3. When we directly use a Map<String, Any> (without wrapping it in the Parameters class), serialization works as expected. This is because Micronaut can serialize the map directly, and the generic Any types inside it are handled accordingly. However, to keep the code clear and structured, we choose to wrap the map in the Parameters class. This approach is more maintainable and self-descriptive.

4. When we tried to use JsonNode as the type for the parameters field, we encountered a different issue. The serialization process did not work as expected, and we received an error indicating that "message": "Internal Server Error: No bean introspection available for type [class ..Request]. Ensure the class is annotated with io.micronaut.core.annotation.Introspected". We used @SerdeImport to import the JsonNode class, but it did not resolve the issue. 