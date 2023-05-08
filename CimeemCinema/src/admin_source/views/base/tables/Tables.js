import React, { useEffect, useState } from "react";
import {
  CButton,
  CCol,
  CForm,
  CFormCheck,
  CFormInput,
  CFormSelect,
  CPagination,
  CPaginationItem,
  CSpinner,
  CTable,
} from "@coreui/react";
import CIcon from "@coreui/icons-react";
import { cilInfo } from "@coreui/icons";

import * as thearteShowTimeService from "../../../../services/thearteShowTimeService";
import * as thearteService from "../../../../services/thearteService";
import * as showTimingService from "../../../../services/showTimingService";
import * as cinemasService from "../../../../services/cinemasService";
import * as locationsService from "../../../../services/locationsService";
import * as moviesService from "../../../../services/moviesService";
import * as roomService from "../../../../services/roomService";
import moment from "moment";
const Tables = () => {
  var curr = new Date();
  curr.setDate(curr.getDate());
  var date = curr.toISOString().substring(0, 10);
  const columns = [
    { key: "theatreName", label: "Theatre Name", _props: { scope: "col" } },
    { key: "time", label: "Time", _props: { scope: "col" } },
    { key: "roomName", label: "Room Name", _props: { scope: "col" } },
    { key: "title", label: "Movie Name", _props: { scope: "col" } },
    { key: "heading_3", label: "Heading", _props: { scope: "col" } },
    { key: "heading_4", label: "Heading", _props: { scope: "col" } },
  ];

  const [loading, setLoading] = useState(false);
  const [visibleModal, setVisibleModal] = useState(false);

  const [items, setItems] = useState([]);
  const [order, setOrder] = useState([]);

  useEffect(() => {
    const fetchApi = async () => {
      const result = await thearteShowTimeService.listBook();
      let cloneChat = result.map((i) => {
        return {
          ...i,
          ...i.theatres,
          ...i.showTimings,
          ...i.room,
          ...i.movie,
          ...{
            heading_4: (
              <button
                style={{ backgroundColor: "transparent", border: "none" }}
              >
                <CIcon
                  icon={cilInfo}
                  size="xl"
                  onClick={() => {
                    setVisibleModal(!visibleModal);
                    setOrder(i);
                  }}
                />
              </button>
            ),

            heading_3: (
              <div>
                <CButton
                  style={{
                    marginRight: "10px",
                  }}
                  color="primary"
                >
                  Primary
                </CButton>
                <CButton
                  style={{
                    marginRight: "10px",
                  }}
                  color="secondary"
                  // onClick={() => deleteMovie(i.movieId)}
                >
                  Delete
                </CButton>
                <CButton color="success">Success</CButton>
              </div>
            ),
          },
        };
      });
      // sua doi
      setItems(cloneChat);
      setLoading(false);
    };

    fetchApi();
  }, [loading]);
  const [itemsThearte, setItemsThearte] = useState([]);
  const [itemsTime, setItemsTime] = useState([]);
  const [itemsCinemas, setItemsCinemas] = useState([]);
  const [itemsMovie, setItemsMovie] = useState([]);
  const [itemRooms, setItemRooms] = useState([]);
  useEffect(() => {
    const fetchApi = async () => {
      const result = await thearteService.listBook();
      setItemsThearte(result);
    };

    fetchApi();
  }, []);

  useEffect(() => {
    const fetchApi = async () => {
      const result = await moviesService.listBook();
      setItemsMovie(result);
    };

    fetchApi();
  }, [loading]);
  const [theatre, setTheatre] = useState("");
  const [show, setShow] = useState("");
  const [cinema, setCinema] = useState("");
  const [movie, setMovie] = useState("");
  const [room, setRoom] = useState("");
  const [dateEnd, setDateEnd] = useState("");
  const [dateStart, setDateStart] = useState("");
  const [status, setStatus] = useState(true);
  const [load, setLoad] = useState(false);
  const handleChange = (event) => {
    setTheatre(event.target.value);
  };
  useEffect(() => {
    console.log(theatre);
    const fetchApi = async () => {
      const result = await cinemasService.listBook(theatre);
      setItemsCinemas(result);
    };

    fetchApi();
  }, [theatre]);
  const handleChange2 = async (event) => {
    setCinema(event.target.value);
  };
  useEffect(() => {
    console.log(theatre);
    const fetchApi = async () => {
      const result = await roomService.listBook(cinema);
      setItemRooms(result);
    };

    fetchApi();
  }, [cinema]);

  const handleChange5 = async (event) => {
    setRoom(event.target.value);
  };
  useEffect(() => {
    const fetchApi = async () => {
      const result = await showTimingService.listBook(theatre, room);
      setItemsTime(result);
    };

    fetchApi();
  }, [room]);

  const handleChange4 = (event) => {
    setMovie(event.target.value);
  };
  const handleChange1 = (event) => {
    setShow(event.target.value);
  };

  const handleSubmit = async (event) => {
    event.preventDefault();
    const response = await thearteShowTimeService.create(
      theatre,
      room,
      show,
      movie,
      cinema,
      dateStart,
      dateEnd,
      status
    );
    window.location.replace("/admin/base/tables");
  };

  return (
    <>
      <CForm validated={true} className="row g-3" onSubmit={handleSubmit}>
        <CCol md={6}>
          <CFormSelect
            feedbackInvalid="Example invalid select feedback"
            aria-label="select example"
            label="Theatre"
            required
            value={theatre}
            onChange={handleChange}
          >
            <option selected="">Open this select menu</option>
            {itemsThearte.map((i) => {
              return <option value={i.theatreId}>{i.theatreName}</option>;
            })}
          </CFormSelect>
        </CCol>
        <CCol md={6}>
          <CFormSelect
            feedbackInvalid="Example invalid select feedback"
            aria-label="select example"
            label="Cinema"
            required
            value={cinema}
            onChange={handleChange2}
          >
            <option selected="" value="">
              Open this select menu
            </option>

            {itemsCinemas != null ? (
              itemsCinemas.map((i) => {
                return (
                  <option
                    // onChange={(e) => setGenres(e.target.value)}
                    value={i.cinemaId}
                  >
                    {i.cinemaName}
                  </option>
                );
              })
            ) : (
              <option>
                {" "}
                <CSpinner />
              </option>
            )}
          </CFormSelect>
        </CCol>
        <div className="row g-3">
          <CCol md={6}>
            <CFormSelect
              feedbackInvalid="Example invalid select feedback"
              aria-label="select example"
              label="Room"
              required
              value={room}
              onChange={handleChange5}
            >
              <option selected="" value="">
                Open this select menu
              </option>
              {itemRooms != null ? (
                itemRooms.map((i) => {
                  return (
                    <option
                      // onChange={(e) => setGenres(e.target.value)}
                      value={i.roomId}
                    >
                      {i.roomName}
                    </option>
                  );
                })
              ) : (
                <option>
                  {" "}
                  <CSpinner />
                </option>
              )}
            </CFormSelect>
          </CCol>
          <CCol md={6}>
            <CFormSelect
              feedbackInvalid="Example invalid select feedback"
              aria-label="select example"
              label="Movie"
              required
              value={movie}
              onChange={handleChange4}
            >
              <option selected="" value="">
                Open this select menu
              </option>
              {itemsMovie.map((i) => {
                return (
                  <option
                    // onChange={(e) => setGenres(e.target.value)}
                    value={i.movieId}
                  >
                    {i.title}
                  </option>
                );
              })}
            </CFormSelect>
          </CCol>
        </div>
        <div className="row g-3">
          <CCol md={6}>
            <CFormInput
              type="date"
              defaultValue={date}
              id="inputEmail4"
              label="Start up date"
              value={moment(dateStart).format("L")}
              onChange={(e) => setDateStart(e.target.value)}
            />
          </CCol>
          <CCol md={6}>
            <CFormInput
              type="date"
              defaultValue={date}
              id="inputEmail6"
              label="Date Ends"
              value={moment(dateEnd).format("L")}
              onChange={(e) => setDateEnd(e.target.value)}
            />
          </CCol>
        </div>
        <div className="row g-3">
          <CCol md={6}>
            <CFormSelect
              feedbackInvalid="Example invalid select feedback"
              aria-label="select example"
              label="Show Time"
              required
              value={show}
              onChange={handleChange1}
            >
              <option selected="" value="">
                Open this select menu
              </option>

              {itemsTime != null ? (
                itemsTime.map((i) => {
                  return (
                    <option
                      // onChange={(e) => setGenres(e.target.value)}
                      value={i.showTimeId}
                    >
                      {i.time}
                    </option>
                  );
                })
              ) : (
                <option>
                  {" "}
                  <CSpinner />
                </option>
              )}
            </CFormSelect>
          </CCol>
        </div>
        <CFormCheck
          className="mb-3"
          id="validationFormCheck1"
          label="Status"
          feedbackInvalid="If you don't check, it won't sell by default, movie showtimes are already arranged"
          required
          value={status}
          onChange={(e) => setStatus(e.target.value)}
        />

        <div className="mb-3">
          <CButton
            // href="/admin/base/tables"
            type="submit"
            color="primary"
            style={{ marginRight: "10px" }}
          >
            Create Theatre Show Times
          </CButton>
          <CButton
            href="/admin/base/placeholders"
            type="submit"
            color="primary"
          >
            Cancel
          </CButton>
        </div>
      </CForm>
      <CTable hover columns={columns} items={items} />
      <CPagination
        style={{ float: "right" }}
        aria-label="Page navigation example"
      >
        <CPaginationItem>Previous</CPaginationItem>
        <CPaginationItem>1</CPaginationItem>
        <CPaginationItem>2</CPaginationItem>
        <CPaginationItem>3</CPaginationItem>
        <CPaginationItem>Next</CPaginationItem>
      </CPagination>
    </>
  );
};

export default Tables;
