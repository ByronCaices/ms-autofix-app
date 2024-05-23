import * as React from "react";
import Box from "@mui/material/Box";
import Drawer from "@mui/material/Drawer";
import List from "@mui/material/List";
import Divider from "@mui/material/Divider";
import ListItemButton from "@mui/material/ListItemButton";
import ListItemIcon from "@mui/material/ListItemIcon";
import ListItemText from "@mui/material/ListItemText";
import CalculateIcon from "@mui/icons-material/Calculate";
import AnalyticsIcon from "@mui/icons-material/Analytics";
import HomeIcon from "@mui/icons-material/Home";
import NoteAddRoundedIcon from "@mui/icons-material/NoteAddRounded";
import AlarmOnRoundedIcon from "@mui/icons-material/AlarmOnRounded";
import CarRepairRoundedIcon from "@mui/icons-material/CarRepairRounded";
import PrecisionManufacturingOutlinedIcon from "@mui/icons-material/PrecisionManufacturingOutlined";
import { useNavigate } from "react-router-dom";

export default function Sidemenu({ open, toggleDrawer }) {
  const navigate = useNavigate();

  const listOptions = () => (
    <Box role="presentation" onClick={toggleDrawer(false)}>
      <List>
        <ListItemButton onClick={() => navigate("/home")}>
          <ListItemIcon>
            <HomeIcon />
          </ListItemIcon>
          <ListItemText primary="Home" />
        </ListItemButton>

        <Divider />

        <ListItemButton onClick={() => navigate("/car/list")}>
          <ListItemIcon>
            <CarRepairRoundedIcon />
          </ListItemIcon>
          <ListItemText primary="Cars" />
        </ListItemButton>

        <ListItemButton onClick={() => navigate("/repair/list")}>
          <ListItemIcon>
            <PrecisionManufacturingOutlinedIcon />
          </ListItemIcon>
          <ListItemText primary="Repairs" />
        </ListItemButton>

        <ListItemButton onClick={() => navigate("/bonus/list")}>
          <ListItemIcon>
            <NoteAddRoundedIcon />
          </ListItemIcon>
          <ListItemText primary="Bonus Discounts" />
        </ListItemButton>

        <Divider />

        <ListItemButton onClick={() => navigate("/report/avgRepairTime")}>
          <ListItemIcon>
            <AlarmOnRoundedIcon />
          </ListItemIcon>
          <ListItemText primary="Average Repair Time" />
        </ListItemButton>

        <ListItemButton onClick={() => navigate("/report/type&bodywork")}>
          <ListItemIcon>
            <CalculateIcon />
          </ListItemIcon>
          <ListItemText primary="Repair Type By Bodywork" />
        </ListItemButton>

        <ListItemButton onClick={() => navigate("/report/type&engine")}>
          <ListItemIcon>
            <AnalyticsIcon />
          </ListItemIcon>
          <ListItemText primary="Repair Type By Engine" />
        </ListItemButton>
      </List>

      <Divider />
    </Box>
  );

  return (
    <div>
      <Drawer anchor={"left"} open={open} onClose={toggleDrawer(false)}>
        {listOptions()}
      </Drawer>
    </div>
  );
}
