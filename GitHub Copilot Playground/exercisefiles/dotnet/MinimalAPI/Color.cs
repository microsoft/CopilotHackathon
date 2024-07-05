public class Color
{
    [JsonPropertyName("name")]
    public string Name { get; set; }

    [JsonPropertyName("category")]
    public string Category { get; set; }

    [JsonPropertyName("type")]
    public string Type { get; set; }

    [JsonPropertyName("code")]
    public ColorCode Code { get; set; }
}

public class ColorCode
{
    [JsonPropertyName("rgba")]
    public int[] RGBA { get; set; }

    [JsonPropertyName("hex")]
    public string HEX { get; set; }
}