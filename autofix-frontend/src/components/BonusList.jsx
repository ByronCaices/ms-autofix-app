import { useEffect, useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import bonusService from "../services/bonus.service";
import Table from "@mui/material/Table";
import TableBody from "@mui/material/TableBody";
import TableCell, { tableCellClasses } from "@mui/material/TableCell";
import TableContainer from "@mui/material/TableContainer";
import TableHead from "@mui/material/TableHead";
import TableRow from "@mui/material/TableRow";
import Paper from "@mui/material/Paper";
import Button from "@mui/material/Button";
import DeleteIcon from "@mui/icons-material/Delete";
import ContactMailRoundedIcon from "@mui/icons-material/ContactMailRounded";
import MoneyRoundedIcon from "@mui/icons-material/MoneyRounded";

const BonusList = () => {
  const [bonuses, setBonuses] = useState([]);

  const navigate = useNavigate();

  const init = () => {
    bonusService
      .getAll()
      .then((response) => {
        console.log("Listing bonuses...", response.data);
        setBonuses(response.data);
      })
      .catch((error) => {
        console.log("An error ocurred while listing bonuses.", error);
      });
  };

  useEffect(() => {
    init();
  }, []);

  const handleDelete = (id) => {
    console.log("Printing id...", id);
    const confirmDelete = window.confirm("Are you sure to delete this bonus?");
    if (confirmDelete) {
      bonusService
        .remove(id)
        .then((response) => {
          console.log("Bonus has been deleted.", response.data);
          init(); // Reload the list of cars
        })
        .catch((error) => {
          console.log(
            "An error ocurred while trying to delete the bonus.",
            error
          );
        });
    }
  };

  const handleEdit = (id) => {
    console.log("Handle Edit Bonus", id);
    navigate(`/bonus/edit/${id}`);
  };

  return (
    <TableContainer component={Paper}>
      <br />
      <h2>Bonus Discount</h2>
      <br /> <br />
      <Link
        to="/bonus/add"
        style={{ textDecoration: "none", marginBottom: "1rem" }}
      >
        <Button
          variant="contained"
          color="primary"
          startIcon={<MoneyRoundedIcon />}
        >
          Add Bonus
        </Button>
      </Link>
      <br /> <br />
      <Table sx={{ minWidth: 650 }} size="small" aria-label="a dense table">
        <TableHead>
          <TableRow>
            <TableCell align="center" sx={{ fontWeight: "bold" }}>
              ID
            </TableCell>
            <TableCell align="center" sx={{ fontWeight: "bold" }}>
              Bonus Discount
            </TableCell>
            <TableCell align="center" sx={{ fontWeight: "bold" }}>
              Brand
            </TableCell>
            <TableCell align="center" sx={{ fontWeight: "bold" }}>
              Stock
            </TableCell>
          </TableRow>
        </TableHead>
        <TableBody>
          {bonuses.map((bonus) => (
            <TableRow
              key={bonus.id}
              sx={{ "&:last-child td, &:last-child th": { border: 0 } }}
            >
              <TableCell align="center">{bonus.id}</TableCell>
              <TableCell align="center">{bonus.bonus}</TableCell>
              <TableCell align="center">{bonus.brand}</TableCell>

              <TableCell align="center">{bonus.stock}</TableCell>
              <TableCell align="left">
                <Button
                  variant="contained"
                  color="info"
                  size="small"
                  onClick={() => handleEdit(bonus.id)}
                  style={{ marginLeft: "0.5rem" }}
                  startIcon={<ContactMailRoundedIcon />}
                >
                  Edit
                </Button>
                <Button
                  variant="contained"
                  color="error"
                  size="small"
                  onClick={() => handleDelete(bonus.id)}
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

export default BonusList;
