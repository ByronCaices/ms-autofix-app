import { useState, useEffect } from "react";
import { Link, useParams, useNavigate } from "react-router-dom";
//import { formatISODateIntl } from "../function";
import repairService from "../services/repair.service";
import Box from "@mui/material/Box";
import TextField from "@mui/material/TextField";
import { format } from "date-fns";
import Button from "@mui/material/Button";
import FormControl from "@mui/material/FormControl";
import MenuItem from "@mui/material/MenuItem";
import SaveIcon from "@mui/icons-material/Save";

const EditRepair = () => {
  const { id } = useParams();
  const [repairPrice, setRepairPrice] = useState("");
  const [repairType, setRepairType] = useState("");
  const [repairCode, setRepairCode] = useState("");
  const [plate, setPlate] = useState("");
  const [bodywork, setBodywork] = useState("");
  const [engine, setEngine] = useState("");
  const [brand, setBrand] = useState("");
  const [mileage, setMileage] = useState("");
  const [discRegClient, setDiscRegClient] = useState("");
  const [discMonThu, setDiscMonThu] = useState("");
  const [discBonus, setDiscBonus] = useState("");
  const [surchCarage, setSurchCarage] = useState("");
  const [surchMileage, setSurchMileage] = useState("");
  const [surchDelay, setSurchDelay] = useState("");
  const [checkinDate, setCheckinDate] = useState("");
  const [finishDate, setFinishDate] = useState("");
  const [checkoutDate, setCheckoutDate] = useState("");
  const [iva, setIva] = useState("");
  const [totalAmount, setTotalAmount] = useState("");
  const [titleRepairForm, setTitleRepairForm] = useState("");

  const navigate = useNavigate();

  const saveRepair = (e) => {
    e.preventDefault();

    const repair = {
      id,
      repairPrice,
      repairType,
      repairCode,
      plate,
      bodywork,
      engine,
      brand,
      mileage,
      discRegClient,
      discMonThu,
      discBonus,
      surchCarage,
      surchMileage,
      surchDelay,
      checkinDate,
      finishDate: finishDate,
      checkoutDate,
      iva,
      totalAmount,
    };
    if (id) {
      //Actualizar Datos
      repairService
        .update(repair)
        .then((response) => {
          console.log("Repair ha sido actualizado.", response.data);
          navigate("/repair/list");
        })
        .catch((error) => {
          console.log("An error ocurred while trying to update repair.", error);
        });
      //repairService.addfinalprice(id);
    } else {
      //Crear nuevo
      repairService
        .create(repair)
        .then((response) => {
          console.log("Repair has been added.", response.data);
          navigate("/repair/list");
        })
        .catch((error) => {
          console.log(repair);
          console.log(
            "An error ocurred while trying tu add new repair.",
            error
          );
        });
    }
    repairService.addfinalprice(id);
  };

  useEffect(() => {
    if (id) {
      setTitleRepairForm("Add Dates of Repair Done & Checkout");
      console.log("Edit Repair getting repair...");
      repairService
        .getById(id)
        .then((repair) => { 
          setRepairPrice(repair.data.repairPrice);
          setRepairType(repair.data.repairType);
          setRepairCode(repair.data.repairCode);
          setPlate(repair.data.plate);
          setBodywork(repair.data.bodywork);
          setEngine(repair.data.engine);
          setBrand(repair.data.brand);
          setMileage(repair.data.mileage);
          setDiscRegClient(repair.data.discRegClient);
          setDiscMonThu(repair.data.discMonThu);
          setDiscBonus(repair.data.discBonus);
          setSurchCarage(repair.data.surchCarage);
          setSurchMileage(repair.data.surchMileage);
          setSurchDelay(repair.data.surchDelay);
          setCheckinDate(repair.data.checkinDate);
          setFinishDate(repair.data.finishDate);
          setCheckoutDate(repair.data.checkoutDate);
          setIva(repair.data.iva);
          setTotalAmount(repair.data.totalAmount);
          console.log("Edit Repair getting repair...", repair.data);
        })
        .catch((error) => {
          console.log("An error ocurred while trying to set repair.", error);
        });
    } else {
      setTitleRepairForm("Register New Repair");
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
      <h2> {titleRepairForm} </h2>
      <hr />

      <h4> Plate: {plate} </h4>

      <ul>
        <li> Bodywork: {bodywork} </li>
        <li> Engine: {engine} </li>
        <li> Brand: {brand} </li>
        <li> Mileage: {mileage} </li>
        <li> Repair Type: {repairType}</li>
        <li> Repair Price: {repairPrice} </li>
      </ul>

      <form>
        <FormControl fullWidth>
          <TextField
            id="finishdate"
            label="Repair Done Date"
            type="datetime-local"
            value={finishDate}
            variant="standard"
            onChange={(e) => setFinishDate(e.target.value)}
            InputLabelProps={{ shrink: true }} // Esto asegura que la etiqueta no se superponga con la fecha seleccionada
          />
        </FormControl>

        <FormControl fullWidth>
          <TextField
            id="checkoutdate"
            label="Checkout Date"
            type="datetime-local"
            value={checkoutDate}
            variant="standard"
            onChange={(e) => setCheckoutDate(e.target.value)}
            InputLabelProps={{ shrink: true }} // Esto asegura que la etiqueta no se superponga con la fecha seleccionada
          />
        </FormControl>

        <FormControl>
          <br />
          <hr />
          <Button
            variant="contained"
            color="info"
            onClick={(e) => saveRepair(e)}
            style={{ marginLeft: "0.5rem" }}
            startIcon={<SaveIcon />}
          >
            Save
          </Button>
        </FormControl>
      </form>
      <hr />
      <Link to="/repair/list">Back to List</Link>
    </Box>
  );
};

export default EditRepair;
