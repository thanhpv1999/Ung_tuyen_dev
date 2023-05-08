import React from "react";
import CIcon from "@coreui/icons-react";
import {
  cilBell,
  cilChartPie,
  cilCursor,
  cilDrop,
  cilNotes,
  cilPencil,
  cilPuzzle,
  cilSpeedometer,
  cilStar,
} from "@coreui/icons";
import { CNavGroup, CNavItem, CNavTitle } from "@coreui/react";

const _nav = [
  {
    component: CNavItem,
    name: "Dashboard",
    to: "/admin/dashboard",
    icon: <CIcon icon={cilSpeedometer} customClassName="nav-icon" />,
    badge: {
      color: "info",
      text: "NEW",
    },
  },
  {
    component: CNavTitle,
    name: "Theme",
  },
  {
    component: CNavItem,
    name: "Booking",
    to: "/admin/theme/colors",
    icon: <CIcon icon={cilDrop} customClassName="nav-icon" />,
  },
  {
    component: CNavItem,
    name: "Orders",
    to: "/admin/theme/typography",
    icon: <CIcon icon={cilPencil} customClassName="nav-icon" />,
  },
  {
    component: CNavTitle,
    name: "Manage",
  },
  {
    component: CNavGroup,
    name: "Movie",
    to: "/admin/base",
    icon: <CIcon icon={cilPuzzle} customClassName="nav-icon" />,
    items: [
      {
        component: CNavItem,
        name: "List Movie",
        to: "/admin/base/placeholders",
      },
      {
        component: CNavItem,
        name: "Create Movie",
        to: "/admin/base/accordion",
      },
      {
        component: CNavItem,
        name: "Genres",
        to: "/admin/base/breadcrumbs",
      },
      {
        component: CNavItem,
        name: "Director",
        to: "/admin/base/cards",
      },
      {
        component: CNavItem,
        name: "Cast",
        to: "/admin/base/carousels",
      },
      {
        component: CNavItem,
        name: "Language",
        to: "/admin/base/collapses",
      },
      {
        component: CNavItem,
        name: "Rate",
        to: "/admin/base/list-groups",
      },
    ],
  },
  {
    component: CNavGroup,
    name: "Theater Manager",
    to: "/admin/buttons",
    icon: <CIcon icon={cilCursor} customClassName="nav-icon" />,
    items: [
      {
        component: CNavItem,
        name: "Theatres",
        to: "/admin/buttons/buttons",
      },
      {
        component: CNavItem,
        name: "Room",
        to: "/admin/forms/validation",
      },
      {
        component: CNavItem,
        name: "Seat Type",
        to: "/admin/forms/range",
      },
      {
        component: CNavItem,
        name: "Seat",
        to: "/admin/forms/checks-radios",
      },
    ],
  },
  {
    component: CNavGroup,
    name: "Show Timings",
    icon: <CIcon icon={cilNotes} customClassName="nav-icon" />,
    items: [
      {
        component: CNavItem,
        name: "Theatre Show Times",
        to: "/admin/forms/form-control",
      },
      {
        component: CNavItem,
        name: "Create Theatre Show Times",
        to: "/admin/base/tables",
      },
      {
        component: CNavItem,
        name: "Create Time",
        to: "/admin/forms/select",
      },
    ],
  },
  {
    component: CNavItem,
    name: "Price",
    to: "/admin/charts",
    icon: <CIcon icon={cilChartPie} customClassName="nav-icon" />,
  },
  {
    component: CNavGroup,
    name: "Personnel Management",
    icon: <CIcon icon={cilStar} customClassName="nav-icon" />,
    items: [
      {
        component: CNavItem,
        name: "Member Users",
        to: "/admin/icons/coreui-icons",
        badge: {
          color: "success",
          text: "NEW",
        },
      },
      {
        component: CNavItem,
        name: "Employee",
        to: "/admin/icons/flags",
      },
    ],
  },
  {
    component: CNavGroup,
    name: "Product",
    icon: <CIcon icon={cilBell} customClassName="nav-icon" />,
    items: [
      {
        component: CNavItem,
        name: "Product Type",
        to: "/admin/notifications/alerts",
      },
    ],
  },

  {
    component: CNavTitle,
    name: "Statistical",
  },
  {
    component: CNavGroup,
    name: "Inventory ",
    icon: <CIcon icon={cilStar} customClassName="nav-icon" />,
    items: [
      {
        component: CNavItem,
        name: "Profit",
        to: "/admin/login",
      },
      {
        component: CNavItem,
        name: "Sales",
        to: "/admin/register",
      },
    ],
  },
];

export default _nav;
