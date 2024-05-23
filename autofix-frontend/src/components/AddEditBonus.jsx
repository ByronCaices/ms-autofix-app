import { useState, useEffect } from "react";
import { Link, useParams, useNavigate } from "react-router-dom";
import bonusService from "../services/bonus.service";
import Box from "@mui/material/Box";
import TextField from "@mui/material/TextField";
import Button from "@mui/material/Button";
import FormControl from "@mui/material/FormControl";
import MenuItem from "@mui/material/MenuItem";
import SaveIcon from "@mui/icons-material/Save";
import { set } from "date-fns";

const AddEditBonus = () => {
  const { id } = useParams();
  const [bonus, setBonus] = useState("");
  const [stock, setStock] = useState("");
  const [brand, setBrand] = useState("");
  const [titleBonusForm, setTitleBonusForm] = useState("");
  const navigate = useNavigate();

  const saveBonus = (e) => {
    e.preventDefault();

    const bonusObj = { id, bonus, stock, brand };

    if (id) {
      //Actualizar Datos
      bonusService
        .update(bonusObj)
        .then((response) => {
          console.log("Bonus ha sido actualizado.", response.data);
          navigate("/bonus/list");
        })
        .catch((error) => {
          //console.log(car)
          console.log("An error ocurred while trying to update bonus.", error);
        });
    } else {
      //Crear nuevo

      bonusService
        .create(bonusObj)
        .then((response) => {
          console.log("Bonus has been added.", response.data);
          navigate("/bonus/list");
        })
        .catch((error) => {
          //console.log(car)
          console.log("An error ocurred while trying tu add new car.", error);
        });
    }
  };

  useEffect(() => {
    if (id) {
      setTitleBonusForm("Edit bonus");
      //console.log("XXXXXX");

      //plate=plateState;
      bonusService
        .getById(id)
        .then((bonusObj) => {
          setBonus(bonusObj.data.bonus);
          setStock(bonusObj.data.stock);
          setBrand(bonusObj.data.brand);
        })
        .catch((error) => {
          console.log("An error ocurred while trying to set bonus.", error);
        });
    } else {
      setTitleBonusForm("Add New Bonus");
    }
  }, []);

  return (
    <Box
      display="flex"
      flexDirection="column"
      alignItems="center"
      justifyContent="center"
      component="form"
    >
      <h3> {titleBonusForm} </h3>
      <hr />
      <form>
        <FormControl fullWidth>
          <TextField
            id="bonus"
            label="Bonus Amount"
            value={bonus}
            type="number"
            variant="standard"
            onChange={(e) => setBonus(e.target.value)}
            helperText="bonus amount per repair"
          />
        </FormControl>

        <FormControl fullWidth>
          <TextField
            id="brand"
            label="Brand"
            value={brand}
            variant="standard"
            onChange={(e) => setBrand(e.target.value)}
          />
        </FormControl>

        <FormControl fullWidth>
          <TextField
            id="stock"
            label="Stock"
            value={stock}
            variant="standard"
            onChange={(e) => setStock(e.target.value)}
          />
        </FormControl>

        
        <FormControl>
          <br />
          <Button
            variant="contained"
            color="info"
            onClick={(e) => saveBonus(e)}
            style={{ marginLeft: "0.5rem" }}
            startIcon={<SaveIcon />}
          >
            Save
          </Button>
        </FormControl>
      </form>
      <hr />
      <Link to="/bonus/list">Back to List</Link>
    </Box>
  );
};

export default AddEditBonus;
