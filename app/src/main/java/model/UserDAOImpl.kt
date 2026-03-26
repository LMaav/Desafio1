package model

class UserDAOImpl : UserDAO{
    companion object {
        private val users = mutableListOf<User>()
    }
    init {
        users.add(User("admin", "123"))
        users.add(User("", ""))
        users.add(User("maria", "vendas"))
        users.add(User("android", "mobile"))
    }
    override fun postUser(user: User) {
        users.add(user)
    }

    override fun getUsers(): List<User> {
        return users
    }

}