let currencyTbl = document.getElementById('exchangeRates')

let requestOptions = {
    method: 'GET'
}

fetch("https://openexchangerates.org/api/latest.json?app_id=4a51a5a828584c5bb2101f089cb42f29&symbols=BGN%2CEUR%2CGBP&prettyprint=false&show_alternative=false", requestOptions)
    .then(response => response.json())
    .then(jasonCurr => jasonCurr.forEach(currency =>
        {
            let row = document.createElement('tr')

            let flagCol = document.createElement('td')
            let currNameCol = document.createElement('td')
            let rateCol = document.createElement('td')

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