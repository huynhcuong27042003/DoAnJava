dayjs.extend(window.dayjs_plugin_advancedFormat);
dayjs.extend(window.dayjs_plugin_isSameOrBefore);
dayjs.extend(window.dayjs_plugin_isSameOrAfter);

let currentMonth = dayjs().startOf('month');
let startDate = dayjs().startOf('day');
let endDate = dayjs().add(1, 'day').startOf('day');

function openCalendarModal() {
    const modal = document.getElementById('calendarModal');
    const modalContent = modal.querySelector('.calendar-modal-content');

    modal.classList.remove('hidden');
    modalContent.classList.remove('modal-close');
    modalContent.classList.add('modal-open');

    renderCalendars();
    updateSelectedDates();
    setDefaultTime();
    populateTimeOptions('pickupTime');
    populateTimeOptions('returnTime');
}

function closeModal() {
    const modal = document.getElementById('calendarModal');
    const modalContent = modal.querySelector('.calendar-modal-content');

    modalContent.classList.remove('modal-open');
    modalContent.classList.add('modal-close');

    modalContent.addEventListener('animationend', function handler() {
        modal.classList.add('hidden');
        modalContent.classList.remove('modal-close');
        modalContent.removeEventListener('animationend', handler);
    });
}

function handleBackdropClick(event) {
    if (event.target === event.currentTarget) {
        closeModal();
    }
}

function prevMonthPair() {
    const newCurrentMonth = currentMonth.subtract(2, 'month');
    if (newCurrentMonth.isBefore(dayjs().startOf('month'), 'month')) {
        return; // Do not allow navigating to past months
    }
    currentMonth = newCurrentMonth;
    renderCalendars();
}

function nextMonthPair() {
    currentMonth = currentMonth.add(2, 'month');
    renderCalendars();
}

function renderCalendars() {
    const currentMonthElement = document.getElementById('currentMonth');
    const nextMonthElement = document.getElementById('nextMonth');

    currentMonthElement.innerHTML = generateCalendar(currentMonth, true);
    nextMonthElement.innerHTML = generateCalendar(currentMonth.add(1, 'month'), false);

    // Hide prev button if at the current month
    document.querySelector('.prev-button').style.display = currentMonth.isSame(dayjs().startOf('month'), 'month') ? 'none' : 'block';
}

function generateCalendar(date, isCurrent) {
    const startOfMonth = date.startOf('month');
    const endOfMonth = date.endOf('month');
    let calendarHtml = `
        <div>
            <div class="calendar-header">
                ${isCurrent ? '<button class="px-2 py-1 text-gray-700 bg-gray-300 rounded prev-button" onclick="prevMonthPair()">&#9664;</button>' : ''}
                <h3 class="font-bold text-center">${date.format('MMMM YYYY')}</h3>
                ${!isCurrent ? '<button class="px-2 py-1 text-gray-700 bg-gray-300 rounded next-button" onclick="nextMonthPair()">&#9654;</button>' : ''}
            </div>
            <div class="grid grid-cols-7 gap-2 mx-3 mt-4 text-center">
                ${['T2', 'T3', 'T4', 'T5', 'T6', 'T7', 'CN'].map(day => `<div class="font-bold">${day}</div>`).join('')}
                ${Array.from({ length: startOfMonth.day() }).map(() => '<div></div>').join('')}
                ${Array.from({ length: endOfMonth.date() }).map((_, i) => {
        const currentDate = startOfMonth.add(i, 'day');
        const isSelected = currentDate.isSame(startDate, 'day') || currentDate.isSame(endDate, 'day');
        const isInRange = currentDate.isAfter(startDate, 'day') && currentDate.isBefore(endDate, 'day');
        const isStartOrEnd = isSelected || isInRange;
        const isDisabled = isCurrent && currentDate.isBefore(dayjs().startOf('day'), 'day');
        return `
                        <div class="${isDisabled ? 'disabled-date' : isSelected ? 'bg-yellow-300' : isStartOrEnd ? 'bg-yellow-500' : 'bg-green-200 cursor-pointer hover:bg-green-400'} rounded" ${isDisabled ? '' : `onclick="selectDate('${currentDate.format('YYYY-MM-DD')}')"`}>
                            ${currentDate.date()}
                        </div>`;
    }).join('')}
            </div>
        </div>
    `;
    return calendarHtml;
}

function selectDate(dateString) {
    const selectedDate = dayjs(dateString);

    if (!startDate || (startDate && endDate)) {
        startDate = selectedDate;
        endDate = null;
    } else if (selectedDate.isBefore(startDate)) {
        endDate = startDate;
        startDate = selectedDate;
    } else {
        endDate = selectedDate;
    }

    renderCalendars();
    updateSelectedDates();
}

function updateSelectedDates() {
    const pickupTime = document.getElementById('pickupTimeDisplay').textContent;
    const returnTime = document.getElementById('returnTimeDisplay').textContent;
    document.getElementById('selectedDates').textContent = `${startDate.format('DD/MM/YYYY')} ${pickupTime} -> ${endDate ? endDate.format('DD/MM/YYYY') + ' ' + returnTime : ''}`;
}

function generateTimeOptions(type) {
    let options = '';
    for (let i = 0; i < 24; i++) {
        for (let j = 0; j < 60; j += 30) {
            const time = `${i.toString().padStart(2, '0')}:${j.toString().padStart(2, '0')}`;
            options += `
                <div id="${type}Option-${time.replace(':', '-')}" class="p-2 cursor-pointer hover:bg-gray-200" onclick="selectTime('${type}', '${time}')">
                    ${time}
                </div>`;
        }
    }
    return options;
}

function populateTimeOptions(type) {
    const menu = document.getElementById(`${type}Menu`);
    menu.innerHTML = generateTimeOptions(type);

    const selectedTime = document.getElementById(`${type}Display`).textContent;
    const selectedOption = document.getElementById(`${type}Option-${selectedTime.replace(':', '-')}`);

    if (selectedOption) {
        selectedOption.classList.add('selected-time');
        menu.scrollTop = selectedOption.offsetTop - menu.clientHeight / 2 + selectedOption.clientHeight / 2;
    }
}

function toggleDropdown(type) {
    document.getElementById(`${type}Menu`).classList.toggle('open');
    populateTimeOptions(type);
}

function selectTime(type, time) {
    document.getElementById(`${type}Display`).textContent = time;
    document.getElementById(`${type}Menu`).classList.remove('open');
    updateSelectedDates();
}

function setDefaultTime() {
    selectTime('pickupTime', '20:00');
    selectTime('returnTime', '21:00');
}

document.addEventListener('click', function (event) {
    if (!event.target.closest('#pickupTimeButton') && !event.target.closest('#pickupTimeMenu')) {
        document.getElementById('pickupTimeMenu').classList.remove('open');
    }
    if (!event.target.closest('#returnTimeButton') && !event.target.closest('#returnTimeMenu')) {
        document.getElementById('returnTimeMenu').classList.remove('open');
    }
});