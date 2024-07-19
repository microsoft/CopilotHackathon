

namespace IntegrationTests;

public class TestWebApplicationFactory<TProgram>
    : WebApplicationFactory<TProgram> where TProgram : class
{
    protected override IHost CreateHost(IHostBuilder builder)
    {
        return base.CreateHost(builder);
    }
}