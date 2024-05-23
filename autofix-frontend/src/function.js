function formatISODateIntl(isoDate) {
    const date = new Date(isoDate);
    const options = {
      year: 'numeric',
      month: 'long',
      day: 'numeric',
      hour: '2-digit',
      minute: '2-digit',
      second: '2-digit',
      timeZoneName: 'short'
    };
    return new Intl.DateTimeFormat('es-ES', options).format(date);
  }


function handleUpdateDateTime(finishDate, timeFinish){
    // Extrayendo solo la parte de la fecha de 'finishDate'
    const datePart = finishDate.split('T')[0];  // Esto divide el string ISO en la fecha y el resto, y toma solo la parte de la fecha.
  
    // Combina la parte de la fecha con el tiempo de 'timeFinish'
    const dateTimeString = `${datePart}T${timeFinish}-04:00`;
    //console.log("Fecha y hora combinadas: ", dateTimeString);

    // Convierte en objeto Date
    const isoDateTime = new Date(dateTimeString).toISOString();

    return isoDateTime;

    //console.log("Fecha actualizada con nueva hora en formato ISO: ", dateTimeString);
    // Aquí puedes hacer más cosas con isoDateTime, como enviarlo a un servidor o usarlo en tu aplicación.
  }

console.log(handleUpdateDateTime("2024-04-27T18:00:00.000-04:00", "23:59"));
console.log(new Date("2024-04-27T18:00:00.000-04:00").toISOString());

