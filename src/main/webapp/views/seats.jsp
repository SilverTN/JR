<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" isELIgnored="false" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Бронирование мест</title>
    <style>
        .free {
            background: green;
            color: white; /* Белый текст на зеленом */
            cursor: pointer;
            border: none;
            padding: 8px 12px;
            margin: 4px;
            border-radius: 4px;
        }
        .reserved {
            background: #d32f2f; /* Более мягкий красный */
            color: #ffebee; /* Светло-розовый текст */
            cursor: not-allowed;
            opacity: 0.9;
            border: none;
            padding: 8px 12px;
            margin: 4px;
            border-radius: 4px;
        }
        button:disabled {
            opacity: 0.9;
        }
        #error-message {
            color: red;
            margin: 10px 0;
            font-weight: bold;
        }
        .passport-info {
            color: #ffcdd2; /* Еще светлее для паспортных данных */
            font-size: 0.95em;
        }
    </style>
    <script>
        function selectSeat(id, status) {
            const errorDiv = document.getElementById('error-message');
            if (status === 'reserved') {
                errorDiv.textContent = 'Это место уже забронировано!';
                return false;
            }
            const passportInput = document.getElementById('passportNumber');
            if (!passportInput.value.trim()) {
                errorDiv.textContent = 'Пожалуйста, введите номер паспорта!';
                passportInput.focus();
                return false;
            }
            errorDiv.textContent = '';
            document.getElementById('seatId').value = id;
            document.getElementById('reserveForm').submit();
        }
    </script>
</head>
<body>
<div id="error-message"></div>
<form id="reserveForm" method="post" action="seats">
    <input type="hidden" id="seatId" name="seatId" />
    <input type="text" id="passportNumber" name="passportNumber" placeholder="Номер паспорта" required />
</form>

<c:choose>
    <c:when test="${not empty seats}">
        <c:forEach var="seat" items="${seats}">
            <button class="${seat.status}"
                    onclick="return selectSeat('${seat.id}', '${seat.status}');"
                ${seat.status eq 'reserved' ? 'disabled' : ''}>
                Место ${seat.id}
                <c:if test="${not empty seat.passportNumber}">
                    <span class="passport-info">(Паспорт: ${seat.passportNumber})</span>
                </c:if>
            </button>
        </c:forEach>
    </c:when>
    <c:otherwise>
        <p>Нет данных о местах для бронирования.</p>
    </c:otherwise>
</c:choose>
</body>
</html>