package model

interface UserDAO {
    fun postUser(user:User)
    fun getUsers():List<User>

}