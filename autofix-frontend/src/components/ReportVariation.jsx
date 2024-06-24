import React, { useEffect, useState } from "react";
import { TextField } from "@mui/material"; // Importa TextField para usarlo como entrada de fecha
import Table from "@mui/material/Table";
import TableBody from "@mui/material/TableBody";
import TableCell from "@mui/material/TableCell";
import TableContainer from "@mui/material/TableContainer";
import TableHead from "@mui/material/TableHead";
import TableRow from "@mui/material/TableRow";
import Paper from "@mui/material/Paper";
import repairService from "../services/repair.service";

const repairTypeNames = {
  1: "1. Brake System",
  2: "2. Cooling System",
  3: "3. Engine",
  4: "4. Transmission",
  5: "5. Electrical System",
  6: "6. Exhaust System",
  7: "7. Tires and Wheels",
  8: "8. Suspension and Steering",
  9: "9. Air Conditioning",
  10: "10. Fuel System",
  11: "11. Windshields and Windows",
};

const ReportVar = () => {
  const [data, setData] = useState({});
  const [selectedDate, setSelectedDate] = useState(""); // Estado para almacenar la fecha seleccionada
  const [months, setMonths] = useState([]);

  useEffect(() => {
    if (selectedDate) {
      // Asegura que selectedDate no sea una cadena vacía
      repairService
        .getReportVariation(selectedDate)
        .then((response) => {
          const monthsExtracted = extractMonths(response.data);
          setMonths(monthsExtracted);
          organizeData(response.data, monthsExtracted);
        })
        .catch((error) => {
          console.error("Error fetching repair data:", error);
        });
    }
  }, [selectedDate]); // Dependencia que rastrea cambios en selectedDate

  const handleDateChange = (event) => {
    setSelectedDate(event.target.value); // Actualiza selectedDate cuando el usuario elige una fecha
  };

  const extractMonths = (fetchedData) => {
    const monthsSet = new Set();
    fetchedData.forEach((item) => {
      monthsSet.add(item[0].trim());
    });
    return Array.from(monthsSet);
  };

  const organizeData = (fetchedData, months) => {
    const organizedData = {};
    Object.keys(repairTypeNames).forEach((typeKey) => {
      organizedData[repairTypeNames[typeKey]] = {};
      months.forEach((month) => {
        organizedData[repairTypeNames[typeKey]][month] = {
          numRepairs: 0,
          repairChange: 0.0,
          totalAmount: 0.0,
          amountChange: 0.0,
        };
      });
    });

    fetchedData.forEach((item) => {
      const [
        month,
        repairType,
        numRepairs,
        repairChange,
        totalAmount,
        amountChange,
      ] = item;
      const repairTypeName = repairTypeNames[repairType];

      if (repairTypeName) {
        organizedData[repairTypeName][month.trim()] = {
          numRepairs,
          repairChange,
          totalAmount,
          amountChange,
        };
      }
    });

    setData(organizedData);
  };

  return (
    <>
      <br />
      <h1>Monthly Report</h1>
      <br />
      <TextField
        label="Select Date"
        type="date"
        value={selectedDate}
        onChange={handleDateChange}
        InputLabelProps={{
          shrink: true,
        }}
        sx={{
          marginBottom: 2,
          "& label": {
            color: "white", // Cambia el color del texto de la etiqueta
          },
          "& input": {
            color: "white", // Cambia el color del texto dentro del input
          },
          "& .MuiInput-underline:before": {
            borderBottomColor: "white", // Cambia el color de la línea inferior antes de hacer click
          },
          "& .MuiInput-underline:hover:not(.Mui-disabled):before": {
            borderBottomColor: "white", // Cambia el color de la línea inferior al hacer hover
          },
          "& .MuiInput-underline:after": {
            borderBottomColor: "white", // Cambia el color de la línea inferior después de seleccionar el input
          },
        }}
      />

      <br />
      <br />
      <TableContainer component={Paper}>
        <Table sx={{ minWidth: 650 }} aria-label="repair table">
          <TableHead>
            <TableRow>
              <TableCell sx={{ fontWeight: "bold" }}>MES</TableCell>
              {months.map((month) => (
                <React.Fragment key={month}>
                  <TableCell align="right" sx={{ fontWeight: "bold" }}>
                    %Var
                  </TableCell>
                  <TableCell align="right" sx={{ fontWeight: "bold" }}>
                    {month}
                  </TableCell>
                </React.Fragment>
              ))}
            </TableRow>
          </TableHead>
          <TableBody>
            {Object.keys(data).map((typeName, index) => (
              <React.Fragment key={typeName}>
                <TableRow>
                  <TableCell
                    component="th"
                    scope="row"
                    sx={{ fontWeight: "bold" }}
                  >
                    {typeName}
                  </TableCell>
                  {months.map((month) => (
                    <React.Fragment key={month}>
                      <TableCell align="right">
                        {(data[typeName][month].repairChange * 100).toFixed(0)}%
                      </TableCell>
                      <TableCell align="right">
                        {data[typeName][month].numRepairs}
                      </TableCell>
                    </React.Fragment>
                  ))}
                </TableRow>
                <TableRow>
                  <TableCell>Total Amount</TableCell>
                  {months.map((month) => (
                    <React.Fragment key={month}>
                      <TableCell align="right">
                        {(data[typeName][month].amountChange * 100).toFixed(0)}%
                      </TableCell>
                      <TableCell align="right">
                        ${data[typeName][month].totalAmount.toFixed(0)}
                      </TableCell>
                    </React.Fragment>
                  ))}
                </TableRow>
                {index !== Object.keys(data).length - 1 && (
                  <TableRow style={{ height: 20, backgroundColor: "#f0f0f0" }}>
                    <TableCell
                      colSpan={months.length * 2 + 1}
                      style={{ borderBottom: "none" }}
                    />
                  </TableRow>
                )}
              </React.Fragment>
            ))}
          </TableBody>
        </Table>
      </TableContainer>
    </>
  );
};

export default ReportVar;
