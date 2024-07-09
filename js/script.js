function calculateEMI() {
    const principal = parseFloat(document.getElementById('principal').value);
    const rate = parseFloat(document.getElementById('rate').value) / 12 / 100;
    const month = parseFloat(document.getElementById('month').value);

    const emi = (principal * rate * Math.pow(1 + rate, month)) / (Math.pow(1 + rate, month) - 1);

    const totalPayment = emi * month;
    const totalInterest = totalPayment - principal;

    document.getElementById('emi').innerText = `Monthly EMI: ${Math.round(emi)}`;
    document.getElementById('totalInterest').innerText = `Total Interest Payable: ${Math.round(totalInterest)}`;
    document.getElementById('totalPayment').innerText = `Total Payment (Principal + Interest): ${Math.round(totalPayment)}`;

    generateMonetizationTable(principal, rate, month, emi);
}

function generateMonetizationTable(principal, rate, month, emi) {
    const tableBody = document.querySelector('#monetizationTable tbody');
    tableBody.innerHTML = '';

    let remainingPrincipal = principal;
    for (let remMonths = 1; remMonths <= month; remMonths++) {
        const interestPayment = remainingPrincipal * rate;
        const principalPayment = emi - interestPayment;
        remainingPrincipal = remainingPrincipal - principalPayment;


        const row = `
            <tr>
                <td>${remMonths}</td>
                <td>${Math.round(principalPayment)}</td>
                <td>${Math.round(interestPayment)}</td>
                <td>${Math.round(emi)}</td>
                <td>${Math.round(remainingPrincipal)}</td>
            </tr>
        `;

        tableBody.innerHTML += row;
    }
}
