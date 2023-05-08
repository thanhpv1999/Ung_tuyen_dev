import React, { useEffect, useState } from "react";
import {
  CButton,
  CFormInput,
  CForm,
  CFormCheck,
  CFormSelect,
  CCol,
} from "@coreui/react";
import ReactImg from "./../../../assets/images/avatars/image.png";
import { EditorState, convertToRaw, ContentState } from "draft-js";
import "react-draft-wysiwyg/dist/react-draft-wysiwyg.css";
import "draft-js/dist/Draft.css";
import { Editor } from "react-draft-wysiwyg";
import "../../../../../node_modules/react-draft-wysiwyg/dist/react-draft-wysiwyg.css";
import draftToHtml from "draftjs-to-html";
import * as genresService from "../../../../services/genresService";
import * as directorService from "../../../../services/directorService";
import * as locationsService from "../../../../services/locationsService";
import * as castService from "../../../../services/castService";
import * as moviesService from "../../../../services/moviesService";
import * as ratingService from "../../../../services/ratingService";
import * as langsService from "../../../../services/langsService";
const Accordion = () => {
  var curr = new Date();
  curr.setDate(curr.getDate());
  var date = curr.toISOString().substring(0, 10);
  const [a, setA] = useState("");
  const onEditorStateChange = (editorState) => {
    // console.log(draftToHtml(convertToRaw(editorState.getCurrentContent())));
    setA(draftToHtml(convertToRaw(editorState.getCurrentContent())));
    setDescription(draftToHtml(convertToRaw(editorState.getCurrentContent())));
    return draftToHtml(convertToRaw(editorState.getCurrentContent()));
  };
  const [items, setItems] = useState([]);
  const [itemsDirector, setItemsDirector] = useState([]);
  const [itemsLocation, setItemsLocation] = useState([]);
  const [itemsCast, setItemsCast] = useState([]);
  const [itemsLang, setItemsLang] = useState([]);
  const [itemsRate, setItemsRate] = useState([]);
  const [loading, setLoading] = useState(false);
  useEffect(() => {
    const fetchApi = async () => {
      setLoading(true);

      const result = await genresService.listBook();
      setItems(result);
    };

    fetchApi();
  }, []);
  useEffect(() => {
    const fetchApi = async () => {
      setLoading(true);

      const result = await directorService.listBook();
      setItemsDirector(result);
    };

    fetchApi();
  }, []);
  useEffect(() => {
    const fetchApi = async () => {
      setLoading(true);

      const result = await locationsService.listBook();
      setItemsLocation(result);
    };

    fetchApi();
  }, []);
  useEffect(() => {
    const fetchApi = async () => {
      setLoading(true);

      const result = await castService.listBook();
      setItemsCast(result);
    };

    fetchApi();
  }, []);
  useEffect(() => {
    const fetchApi = async () => {
      setLoading(true);

      const result = await ratingService.listBook();
      setItemsRate(result);
    };

    fetchApi();
  }, []);
  useEffect(() => {
    const fetchApi = async () => {
      setLoading(true);

      const result = await langsService.listBook();
      setItemsLang(result);
    };

    fetchApi();
  }, []);
  const [title, setTitle] = useState("");
  const [url, setUrl] = useState("");
  const [genres, setGenres] = useState("");
  const [location, setLocation] = useState("");
  const [director, setDirector] = useState("");
  const [cast, setCast] = useState("");
  const [description, setDescription] = useState("");
  const [duration, setDuration] = useState("");
  const [image, setImage] = useState("");
  const [dateAraid, setDateAraid] = useState("");
  const [dateStart, setDateStart] = useState("");
  const [status, setStatus] = useState(true);
  const [rate, setRate] = useState("");
  const [lang, setLang] = useState("");
  const handleChange = (event) => {
    setGenres(event.target.value);
  };
  const handleChange1 = (event) => {
    setDirector(event.target.value);
  };
  const handleChange2 = (event) => {
    setLocation(event.target.value);
  };
  const handleChange3 = (event) => {
    setCast(event.target.value);
  };
  const handleChange4 = (event) => {
    setRate(event.target.value);
  };
  const handleChange5 = (event) => {
    setLang(event.target.value);
  };
  const handleSubmit = async (event) => {
    event.preventDefault();
    // const createMovie: (title: any, description: any, durationMin: any,
    //    rateId: any, pathImg: any, urlTrailer: any, startUpDate: any,
    // languageId: any,
    //    dateAired: any,
    //   values: any, actor: any, director: any, location: any) => Promise<...>
    const response = await moviesService.createMovie(
      title,
      description,
      duration,
      rate,
      image,
      url,
      dateStart,
      lang,
      dateAraid,
      genres,
      cast,
      director,
      location
    );
    window.location.replace("/admin/base/tables");
  };
  const handleUpLoadFile = async (e) => {
    const listFile_size = e.target.files.length;
    for (var i = 0; i < listFile_size; i++) {
      const formData = new FormData();
      formData.append("img[]", e.target.files[i]);
      const response = await moviesService.uploadImg(formData);
      setImage(response);
    }
  };

  return (
    <CForm validated={true} onSubmit={handleSubmit}>
      <div className="row g-3">
        <CCol md={6}>
          <CFormInput
            type="text"
            id="inputEmail4"
            label="Title"
            value={title}
            onChange={(e) => setTitle(e.target.value)}
          />
        </CCol>
        <CCol md={6}>
          <CFormInput
            type="text"
            id="inputEmail4"
            label="Url Video"
            value={url}
            onChange={(e) => setUrl(e.target.value)}
          />
        </CCol>
        <CCol md={6}>
          <CFormSelect
            feedbackInvalid="Example invalid select feedback"
            aria-label="select example"
            label="Genres"
            required
            value={genres}
            onChange={handleChange}
          >
            <option selected="">Open this select menu</option>
            {items.map((i) => {
              return (
                <option
                  onChange={(e) => setGenres(e.target.value)}
                  value={i.genresId}
                >
                  {i.genres}
                </option>
              );
            })}
          </CFormSelect>
        </CCol>
        <CCol md={6}>
          <CFormSelect
            feedbackInvalid="Example invalid select feedback"
            aria-label="select example"
            label="Director"
            required
            onChange={handleChange1}
            value={director}
          >
            <option selected="">Open this select menu</option>
            {itemsDirector.map((i) => {
              return (
                <option
                  value={i.directorId}
                  onChange={(e) => setDirector(e.target.value)}
                >
                  {i.name}
                </option>
              );
            })}
          </CFormSelect>
        </CCol>
      </div>
      <br />
      <div className="row g-3">
        <CCol md={6}>
          <CFormSelect
            feedbackInvalid="Example invalid select feedback"
            aria-label="select example"
            label="Location"
            required
            value={location}
            onChange={handleChange2}
          >
            <option selected="">Open this select menu</option>
            {itemsLocation.map((i) => {
              return (
                <option
                  value={i.locationId}
                  onChange={(e) => setLocation(e.target.value)}
                >
                  {i.location}
                </option>
              );
            })}
          </CFormSelect>
        </CCol>
        <CCol md={6}>
          <CFormSelect
            feedbackInvalid="Example invalid select feedback"
            aria-label="select example"
            label="Cast"
            required
            value={cast}
            onChange={handleChange3}
          >
            <option selected="">Open this select menu</option>
            {itemsCast.map((i) => {
              return (
                <option
                  value={i.castId}
                  onChange={(e) => setCast(e.target.value)}
                >
                  {i.name}
                </option>
              );
            })}
          </CFormSelect>
        </CCol>
      </div>
      <br />
      <div className="row g-3">
        <CCol md={6}>
          {/* <CFormTextarea
            feedbackInvalid="Please enter a message in the textarea."
            id="validationTextarea"
            label="Description"
            placeholder="Required example textarea"
            required
          ></CFormTextarea> */}
        </CCol>
      </div>
      <label>Description</label>
      <Editor
        toolbarClassName="toolbarClassName"
        wrapperClassName="wrapperClassName"
        editorClassName="editorClassName"
        onEditorStateChange={onEditorStateChange}
        value={description}
      />
      <div dangerouslySetInnerHTML={{ __html: a }} />
      <div className="row g-3">
        <CCol md={6}>
          <CFormInput
            type="number"
            id="inputEmail4"
            label="Duration Min"
            value={duration}
            onChange={(e) => setDuration(e.target.value)}
          />
        </CCol>
        <CCol md={6}>
          <CFormInput
            label="Chose Image Movie"
            type="file"
            id="validationTextarea"
            feedbackInvalid="Example invalid form file feedback"
            aria-label="file example"
            required
            onChange={handleUpLoadFile}
          />
        </CCol>
      </div>
      <div className="row g-3">
        <CCol md={6}>
          <CFormInput
            type="date"
            defaultValue={date}
            id="inputEmail4"
            label="Aired up date"
            value={dateAraid}
            onChange={(e) => setDateAraid(e.target.value)}
          />
        </CCol>
        <CCol md={6}>
          <CFormInput
            type="date"
            defaultValue={date}
            id="inputEmail4"
            label="Start up date"
            value={dateStart}
            onChange={(e) => setDateStart(e.target.value)}
          />
        </CCol>
        <CCol md={6}>
          <input
            hidden
            id="input"
            type="file"
            onChange={handleUpLoadFile}
          ></input>
          <label for="input" style={{ marginRight: "500px" }}>
            <img style={{ marginTop: 8 }} src={ReactImg} alt="" width="300%" />
          </label>
        </CCol>
        <CCol md={6}>
          <CFormSelect
            feedbackInvalid="Example invalid select feedback"
            aria-label="select example"
            label="Director"
            required
            onChange={handleChange4}
            value={rate}
          >
            <option selected="">Open this select menu</option>
            {itemsRate.map((i) => {
              return (
                <option
                  value={i.rateId}
                  onChange={(e) => setDirector(e.target.value)}
                >
                  {i.status}
                </option>
              );
            })}
          </CFormSelect>
        </CCol>
        <CCol md={6}>
          <CFormSelect
            feedbackInvalid="Example invalid select feedback"
            aria-label="select example"
            label="Langs"
            required
            onChange={handleChange5}
            value={lang}
          >
            <option selected="">Open this select menu</option>
            {itemsLang.map((i) => {
              return (
                <option
                  value={i.languageId}
                  onChange={(e) => setDirector(e.target.value)}
                >
                  {i.decripstion}
                </option>
              );
            })}
          </CFormSelect>
        </CCol>
      </div>
      <CFormCheck
        className="mb-3"
        id="validationFormCheck1"
        label="Open ticket sales"
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
          Create Movie
        </CButton>
        <CButton href="/admin/base/placeholders" type="submit" color="primary">
          Cancel
        </CButton>
      </div>
    </CForm>
  );
};

export default Accordion;
