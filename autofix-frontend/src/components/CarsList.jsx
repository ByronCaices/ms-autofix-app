import { useEffect, useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import carService from "../services/car.service";
import Table from "@mui/material/Table";
import TableBody from "@mui/material/TableBody";
import TableCell, { tableCellClasses } from "@mui/material/TableCell";
import TableContainer from "@mui/material/TableContainer";
import TableHead from "@mui/material/TableHead";
import TableRow from "@mui/material/TableRow";
import Paper from "@mui/material/Paper";
import Button from "@mui/material/Button";
import EditIcon from "@mui/icons-material/Edit";
import DeleteIcon from "@mui/icons-material/Delete";
import MinorCrashRoundedIcon from "@mui/icons-material/MinorCrashRounded";

const CarList = () => {
  const [cars, setCars] = useState([]);

  const navigate = useNavigate();

  const init = () => {
    carService
      .getAll()
      .then((response) => {
        console.log("Listing cars...", response.data);
        setCars(response.data);
      })
      .catch((error) => {
        console.log("An error ocurred while listing cars.", error);
      });
  };

  useEffect(() => {
    init();
  }, []);

  const handleDelete = (plate) => {
    console.log("Printing plate...", plate);
    const confirmDelete = window.confirm("Are you sure to delete this car?");
    if (confirmDelete) {
      carService
        .remove(plate)
        .then((response) => {
          console.log("Car has been deleted.", response.data);
          init(); // Reload the list of cars
        })
        .catch((error) => {
          console.log(
            "An error ocurred while trying to delete the car.",
            error
          );
        });
    }
  };

  const handleEdit = (plate) => {
    console.log("handleEdit", plate);
    navigate(`/car/edit/${plate}`);
  };

  return (
    <TableContainer component={Paper}>
      <br />
      <h2>Cars Register</h2>
      <br /> <br />
      <Link
        to="/car/add"
        style={{ textDecoration: "none", marginBottom: "1rem" }}
      >
        <Button
          variant="contained"
          color="primary"
          startIcon={<MinorCrashRoundedIcon />}
        >
          Add Car
        </Button>
      </Link>
      <br /> <br />
      <Table sx={{ minWidth: 650 }} size="small" aria-label="a dense table">
        <TableHead>
          <TableRow>
            <TableCell align="left" sx={{ fontWeight: "bold" }}>
              Plate
            </TableCell>
            <TableCell align="left" sx={{ fontWeight: "bold" }}>
              Bodywork
            </TableCell>
            <TableCell align="left" sx={{ fontWeight: "bold" }}>
              Engine
            </TableCell>
            <TableCell align="center" sx={{ fontWeight: "bold" }}>
              Brand
            </TableCell>
            <TableCell align="right" sx={{ fontWeight: "bold" }}>
              Model
            </TableCell>
            <TableCell align="right" sx={{ fontWeight: "bold" }}>
              Mileage
            </TableCell>
            <TableCell align="right" sx={{ fontWeight: "bold" }}>
              Year
            </TableCell>
            <TableCell align="right" sx={{ fontWeight: "bold" }}>
              Seats
            </TableCell>
          </TableRow>
        </TableHead>
        <TableBody>
          {cars.map((car) => (
            <TableRow
              key={car.plate}
              sx={{ "&:last-child td, &:last-child th": { border: 0 } }}
            >
              <TableCell align="left">{car.plate}</TableCell>
              <TableCell align="left">{car.bodywork}</TableCell>
              <TableCell align="left">{car.engine}</TableCell>
              <TableCell align="center">{car.brand}</TableCell>
              <TableCell align="right">{car.model}</TableCell>
              <TableCell align="right">{car.mileage}</TableCell>
              <TableCell align="right">{car.year}</TableCell>
              <TableCell align="right">{car.seats}</TableCell>
              <TableCell align="center">
                <Button
                  variant="contained"
                  color="info"
                  size="small"
                  onClick={() => handleEdit(car.plate)}
                  style={{ marginLeft: "0.5rem" }}
                  startIcon={<EditIcon />}
                >
                  Edit
                </Button>

                <Button
                  variant="contained"
                  color="error"
                  size="small"
                  onClick={() => handleDelete(car.plate)}
                  style={{ marginLeft: "0.5rem" }}
                  startIcon={<DeleteIcon />}
                >
                  Delete
                </Button>
              </TableCell>
            </TableRow>
          ))}
        </TableBody>
      </Table>
    </TableContainer>
  );
};

export default CarList;
