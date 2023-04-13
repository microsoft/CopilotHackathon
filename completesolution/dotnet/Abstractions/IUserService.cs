// Create a new IUserService interface with GetUser, GetUserById, AddUser, UpdateUser, DeleteUser methods

namespace Abstractions;
public interface IUserService
{
    IEnumerable<User> GetUsers();
    User GetUser(int id);
    User AddUser(User user);
    User UpdateUser(int id, User user);
    User DeleteUser(int id);
}