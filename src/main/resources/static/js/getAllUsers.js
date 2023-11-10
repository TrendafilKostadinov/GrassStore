// <tr th:each="user : ${users}">
//     <td th:text="${users.indexOf(user) + 1}"></td>
//     <td th:text="${user.email}"></td>
//     <td th:text="${user.fullName}"></td>
//     <td><a className="btn shadow-lg btn-outline-dark" th:href="@{/user-detail/{id}(id=*{user.getId()})}">User
//         details</a></td>
//     <td><a className="btn shadow-lg btn-outline-dark" th:href="@{/admin/user-password/{id}(id=*{user.getId()})}">Change
//         password</a></td>
// </tr>

let usersTbl = document.getElementById('allUsersTable')

let requestOptions = {
    method: 'GET'
}

let counter = 1

fetch("http://localhost:8080/all-users", requestOptions)
    .then(response => response.json())
    .then(jason => jason.forEach(user =>
        {
            let row = document.createElement('tr')

            let indexCol = document.createElement('td')
            let emailCol = document.createElement('td')
            let fullNameCol = document.createElement('td')
            let userDetailsBtn = document.createElement('td')
            let changePasswordBtn = document.createElement('td')

            indexCol.innerHTML = counter.toString()
            counter += 1

            emailCol.innerHTML = user.email
            fullNameCol.innerHTML = user.fullName

            // userDetailsBtn.innerHTML = '<a className="btn shadow-lg btn-outline-dark" href="/user-detail/' user.id + '>User details</a>'

            row.appendChild(indexCol)
            row.appendChild(emailCol)
            row.appendChild(fullNameCol)
            row.appendChild(userDetailsBtn)
            row.appendChild(changePasswordBtn)

            usersTbl.appendChild(row)
        }
    ))