using Abstractions;

public class UserService : IUserService
{
    // create in-memory list of users
    private List<User> users = new List<User>()
    {
        
    };
    public User AddUser(User user)
    {
        // Add user to list
        users.Add(user);
        return this.GetUser(user.Id);
    }

    public User DeleteUser(int id)
    {
        throw new NotImplementedException();
    }

    public User GetUser(int id)
    {
        // implement logic to get user by id
        return users.FirstOrDefault(u => u.Id == id);
    }

    public IEnumerable<User> GetUsers()
    {
        // implement logic to get all users
        return users;
    }

    public User UpdateUser(int id, User user)
    {
        throw new NotImplementedException();
    }
}


 