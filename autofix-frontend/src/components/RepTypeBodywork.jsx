import { useEffect, useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import repairService from "../services/repair.service";
import Table from "@mui/material/Table";
import TableBody from "@mui/material/TableBody";
import TableCell, { tableCellClasses } from "@mui/material/TableCell";
import TableContainer from "@mui/material/TableContainer";
import TableHead from "@mui/material/TableHead";
import TableRow from "@mui/material/TableRow";
import Paper from "@mui/material/Paper";

const RepTypeBodywork = () => {
  const [reports, setReports] = useState([]);

  const navigate = useNavigate();

  const init = () => {
    repairService
      .getReportBodywork()
      .then((response) => {
        console.log("Listing reports...", response.data);
        setReports(response.data);
      })
      .catch((error) => {
        console.log("An error ocurred while listing reports.", error);
      });
  };

  useEffect(() => {
    init();
  }, []);

  return (
    <TableContainer component={Paper}>
      <br />
      <h2>Repair Type By Bodywork</h2>
      <br /> <br />
      <Table sx={{ minWidth: 650 }} size="small" aria-label="a dense table">
        <TableHead>
          <TableRow>
            <TableCell align="left" sx={{ fontWeight: "bold" }}>
              Repair Type
            </TableCell>
            <TableCell align="left" sx={{ fontWeight: "bold" }}>
              Bodywork
            </TableCell>

            <TableCell align="left" sx={{ fontWeight: "bold" }}>
              Numbers of Repairs
            </TableCell>
            <TableCell align="left" sx={{ fontWeight: "bold" }}>
              Total Sales
            </TableCell>
          </TableRow>
        </TableHead>
        <TableBody>
          {reports.map((report) => (
            <TableRow
              key={report[0]}
              sx={{ "&:last-child td, &:last-child th": { border: 0 } }}
            >
              <TableCell align="left">{report[0]}</TableCell>
              <TableCell align="left">{report[3]}</TableCell>

              <TableCell align="left">{report[2]}</TableCell>
              <TableCell align="left">${report[1]}</TableCell>
            </TableRow>
          ))}
        </TableBody>
      </Table>
    </TableContainer>
  );
};

export default RepTypeBodywork;
