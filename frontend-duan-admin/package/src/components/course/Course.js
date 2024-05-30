import React, { useState, useEffect, useCallback } from "react";
import axios from "axios";
import { Card, CardTitle, Table, FormGroup, Label, Input } from "reactstrap";
import { toast } from 'react-toastify';

import Form from "react-bootstrap/Form";
import Modal from 'react-bootstrap/Modal';
import Button from "react-bootstrap/Button";
import Col from 'react-bootstrap/Col';
import Row from 'react-bootstrap/Row';

import Select from 'react-select';
import makeAnimated from 'react-select/animated';

import { useNavigate } from "react-router-dom";
import UsePagination from "../../hooks/pagination";
import noImg from "../../assets/images/no-img.jpg";
import "../../assets/scss/course.scss"

const Course = () => {
    const [course, setCourse] = useState({ courseSubjects: [] })
    const [file, setFile] = useState()
    const [checkeds, setCheckeds] = useState([])
    const navigate = useNavigate();
    const [page, setPage] = useState(0)
    const [size, setSize] = useState(7)
    const [keyword, setKeyword] = useState('')
    const [isSort, setIsSort] = useState(false)
    const [modalCreateShow, setModalCreateShow] = useState(false);
    const [modalDeleteShow, setModalDeleteShow] = useState(false);

    const animatedComponents = makeAnimated();

    // call api 
    const [courses, setCourses] = useState()
    const [subjects, setSubjects] = useState()

    const getApiCourse = useCallback(async (keyword) => {
        try {
            const res = await axios.get(`http://localhost:8080/api/v1/course/getPage?keyword=${keyword ? keyword : ''}&&page=${page}&&size=${size}`);
            const data = res.data ? res.data : []
            setCourses(data)
        } catch (e) {

        }
    }, [page, size])

    const getApiSubject = useCallback(async () => {
        try {
            const res = await axios.get(`http://localhost:8080/api/v1/subject/getAll`);
            const data = res.data ? res.data : []
            let fakeData = []
            data.map(item => (
                fakeData.push({ label: item.name, value: item.id, subject: { id: item.id } })
            ))
            setSubjects(fakeData)
        } catch (e) {

        }
    }, [])

    useEffect(() => {
        getApiCourse(keyword);
    }, [page, size])

    useEffect(() => {
        getApiSubject();
    }, [getApiSubject])


    const createCourse = async () => {
        try {
            const formData = new FormData();
            formData.append('courseDTO', new Blob([JSON.stringify(course)], { type: 'application/json' }));
            formData.append('file', file ? file : {}); // Assuming 'file' is a File object
            await axios.post(`http://localhost:8080/api/v1/course/create`, formData, {
                headers: {
                    'Content-Type': 'multipart/form-data'
                }
            })
            getApiCourse(keyword)
            closeModalCreate()
            toast.success("Thêm khóa học thành công");
        } catch (error) {
        }
    }


    const deleteCourse = async (isStatus) => {
        if (isStatus) {
            const lst = [...checkeds]
            await axios.post(`http://localhost:8080/api/v1/course/delete`, lst)
            getApiCourse(keyword)
            setModalDeleteShow(false)
            toast.success("Xóa khóa học thành công");
        }
    }

    // End Call api

    const handleSearch = (kw) => {
        setKeyword(kw)
        if (!kw) {
            getApiCourse('')
        }
    }

    // chuyển đến trang chi tiết khóa học
    const openDetail = (id) => {
        navigate(`${id}`);
    }

    // xử lý checked
    const handleChecked = (id) => {
        let copy = [...checkeds]
        const index = copy.indexOf(id)
        if (index === -1) {
            copy.push(id)
            setCheckeds((prev) => [...prev, id])
        } else {
            copy.splice(index, 1)
            setCheckeds([...copy])
        }
        if (copy.length === courses.content.length) {
            document.getElementById("check-all").checked = true
            return
        } else {
            document.getElementById("check-all").checked = false
        }
    }

    const handleCheckedAll = (checked) => {
        setCheckeds([])
        if (checked) {
            courses.content.map(item => (
                setCheckeds((prev) => [...prev, item.id])
            ))
        }
    }

    // End checked

    // sắp xếp data
    const sortData = (id) => {
        if (!id) return
        setIsSort(!isSort)
        if (!isSort) {
            sortDataGraduallySmaller(id)
        } else {
            sortDataAscending(id)
        }
    }

    const sortDataAscending = (data) => {
        let newData = [...courses.content]
        if (!newData?.map(p => p[data])) return
        newData?.sort((a, b) => a[data] > b[data] ? 1 : -1)
        setCourses((prev) => ({ ...prev, content: newData }))
    }

    const sortDataGraduallySmaller = (data) => {
        let newData = [...courses.content]
        if (!newData?.map(p => p[data])) return
        newData?.sort((a, b) => a[data] < b[data] ? 1 : -1)
        setCourses((prev) => ({ ...prev, content: newData }))
    }
    // End sort

    const [validated, setValidated] = useState(false);

    const handleCreate = () => {
        const form = document.getElementById('create-course-form').checkValidity();
        if (form === false) {
            // show điều khiện form
            setValidated(true);
            return
        }
        createCourse()
    };

    const handleSelect = (value) => {
        const data = { ...course };
        data.courseSubjects = value
        setCourse(data)
    }

    const handleInput = (input, value) => {
        if (input === 'price' || input === 'totalCourseDuration') {
            if (value !== "") {
                if (isNaN(value)) return;
                if (value <= 0) return;
            }
        }
        setCourse((prev) => ({ ...prev, [input]: value }))
    }

    const closeModalCreate = () => {
        setModalCreateShow(false);
        setCourse({ courseSubjects: [] })
        setFile()
    }

    const inputFile = () => {
        document.getElementById("input-file").click()
    }


    return (
        <div>
            <Card>
                <CardTitle tag="h6" className="border-bottom p-3 mb-0 course-cart-header">
                    <Form className="d-flex search-data">
                        <Form.Control
                            type="search"
                            placeholder="Search"
                            className="me-2"
                            aria-label="Search"
                            value={keyword}
                            onChange={(e) => handleSearch(e.target.value)}
                        />
                        <Button variant="outline-success" onClick={() => getApiCourse(keyword)}>Search</Button>
                    </Form>
                    <div className="box-action">
                        {checkeds.length > 0 &&
                            <div className="box-delete" onClick={() => setModalDeleteShow(true)}>
                                <i className="bi bi-archive"></i>
                                Xóa
                            </div>
                        }
                        <div className="box-add" onClick={() => setModalCreateShow(true)}>
                            <i className="bi bi-plus-circle"></i>
                            Thêm
                        </div>
                    </div>
                </CardTitle>
                {courses &&
                    <>
                        <Table className="no-wrap mt-3 align-middle" responsive borderless>
                            <thead>
                                <tr>
                                    <th colSpan={2}>
                                        <FormGroup check>
                                            <Input id="check-all" type="checkbox" onChange={(e) => handleCheckedAll(e.target.checked)} /> <Label check>All</Label>
                                        </FormGroup>
                                    </th>
                                    <th>Code</th>
                                    <th onClick={() => sortData('name')}>Tên</th>
                                    <th>Giới thiệu</th>
                                    <th onClick={() => sortData('price')}>Giá</th>
                                    <th onClick={() => sortData('numberOfStudent')}>Đang học</th>
                                    <th onClick={() => sortData('numberOfPurchases')}>Đăng ký</th>
                                    <th onClick={() => sortData('totalCourseDuration')}>Thời gian</th>
                                </tr>
                            </thead>
                            <tbody>
                                {courses.content.map(item => (
                                    <tr key={item.id} className="border-top">
                                        <td>
                                            <FormGroup check className="check-item">
                                                <Input type="checkbox" onChange={() => { handleChecked(item.id) }} checked={checkeds.includes(item.id)} />
                                                <img className="course-item-img" src={item.imageCourse ? item.imageCourse : noImg} alt="" />
                                            </FormGroup>
                                        </td>
                                        <td style={{ paddingLeft: 0, paddingRight: 0 }}>
                                        </td>
                                        <td>
                                            {item.code}
                                        </td>
                                        <td>
                                            {item.name}
                                        </td>
                                        <td>
                                            {item.introduce}
                                        </td>
                                        <td>
                                            {item.price}
                                        </td>
                                        <td>
                                            {item.numberOfStudent}
                                        </td>
                                        <td>
                                            {item.numberOfPurchases}
                                        </td>
                                        <td>
                                            {item.totalCourseDuration}
                                        </td>

                                        <td>
                                            <svg onClick={() => openDetail(item.id)} style={{ cursor: "pointer" }} xmlns="http://www.w3.org/2000/svg" height="20px" viewBox="0 -960 960 960" width="20px" fill="#666666"><path d="M216-144q-29.7 0-50.85-21.15Q144-186.3 144-216v-528q0-30.11 21-51.56Q186-817 216-816h346l-72 72H216v528h528v-274l72-72v346q0 29.7-21.15 50.85Q773.7-144 744-144H216Zm264-336Zm-96 96v-153l354-354q11-11 24-16t26.5-5q14.4 0 27.45 5 13.05 5 23.99 15.78L891-840q11 11 16 24.18t5 26.82q0 13.66-5.02 26.87-5.02 13.2-15.98 24.13L537-384H384Zm456-405-51-51 51 51ZM456-456h51l231-231-25-26-26-25-231 231v51Zm257-257-26-25 26 25 25 26-25-26Z" /></svg>
                                        </td>
                                    </tr>
                                ))}
                            </tbody>
                        </Table>
                        <UsePagination
                            totalPages={courses.totalPages}
                            number={courses.number + 1}
                            setPage={setPage}
                        />
                    </>
                }
            </Card>




            <Modal
                show={modalCreateShow}
                onHide={() => closeModalCreate()}
                size="lg"
                aria-labelledby="contained-modal-title-vcenter"
                centered
            >
                <Modal.Header closeButton>
                    <Modal.Title id="contained-modal-title-vcenter">
                        Thêm khóa học
                    </Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <Form noValidate validated={validated} id='create-course-form'>
                        <Row>
                            <Col md="8">
                                <Row className="mb-3">
                                    <Form.Group as={Col} md="6" controlId="validationCustom01">
                                        <Form.Label>Tên khóa học</Form.Label>
                                        <Form.Control
                                            required
                                            type="text"
                                            placeholder="Tên khóa học"
                                            onChange={(e) => handleInput('name', e.target.value)}
                                            value={course.name ? course.name : ""}
                                        />
                                        <Form.Control.Feedback type="invalid">Vui lòng nhập tên khóa học</Form.Control.Feedback>
                                    </Form.Group>
                                    <Form.Group as={Col} md="6" controlId="validationCustom03">
                                        <Form.Label>Giá khóa học</Form.Label>
                                        <Form.Control type="number" required
                                            onChange={(e) => handleInput('price', e.target.value)}
                                            value={course.price ? course.price : ""}
                                        />
                                        <Form.Control.Feedback type="invalid">
                                            Vui lòng nhập giá khóa học
                                        </Form.Control.Feedback>
                                    </Form.Group>
                                </Row>
                                <Row className="mb-3">
                                    <Form.Group as={Col} md="6" controlId="validationCustom02">
                                        <Form.Label>Giới thiệu khóa học</Form.Label>
                                        <Form.Control
                                            required
                                            type="text"
                                            placeholder=""
                                            onChange={(e) => handleInput('introduce', e.target.value)}
                                            value={course.introduce ? course.introduce : ""}
                                        />
                                        <Form.Control.Feedback type="invalid">Vui lòng nhập giới thiệu của khóa học</Form.Control.Feedback>
                                    </Form.Group>
                                    <Form.Group as={Col} md="6" controlId="validationCustom04">
                                        <Form.Label>Thời lượng khóa học</Form.Label>
                                        <Form.Control type="number" required
                                            onChange={(e) => handleInput('totalCourseDuration', e.target.value)}
                                            value={course.totalCourseDuration ? course.totalCourseDuration : ""}
                                        />
                                        <Form.Control.Feedback type="invalid">
                                            Vui lòng nhập thời lượng khóa học
                                        </Form.Control.Feedback>
                                    </Form.Group>
                                </Row>
                                <Form.Group controlId="validationCustom04">
                                    <Form.Label>Chọn môn học cho khóa học</Form.Label>
                                    <Select
                                        closeMenuOnSelect={false}
                                        components={animatedComponents}
                                        isMulti
                                        options={subjects}
                                        onChange={(e) => handleSelect(e)}
                                        value={course.courseSubjects}
                                        required
                                    />
                                    <Form.Control.Feedback type="invalid">
                                        Vui lòng chọn môn học cho khóa học
                                    </Form.Control.Feedback>
                                </Form.Group>
                            </Col>
                            <Col md="4">
                                <input style={{
                                    border: "1px solid",
                                    width: "100%",
                                    borderRadius: "5px",
                                    display: 'none'
                                }}
                                    id="input-file"
                                    onChange={(e) => setFile(e.target.files[0])} type="file" />
                                <div className="course-img" onClick={() => inputFile()} style={{ backgroundImage: `url(${file ? URL.createObjectURL(file) : noImg})` }} />
                            </Col>
                        </Row>
                    </Form>
                </Modal.Body>
                <Modal.Footer>
                    <Button variant="outline-primary" onClick={() => handleCreate()}>Thêm</Button>
                    <Button variant="outline-secondary" onClick={() => closeModalCreate()}>Đóng</Button>
                </Modal.Footer>
            </Modal>


            <Modal
                show={modalDeleteShow}
                onHide={() => setModalDeleteShow(false)}
                aria-labelledby="contained-modal-title-vcenter"
                centered
            >
                <Modal.Header closeButton>
                    <Modal.Title id="contained-modal-title-vcenter">
                        Xóa khóa học
                    </Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    Bạn chắc chắn muốn xóa {checkeds?.length > 1 ? "những khóa học này?" : "khóa học này?"}
                </Modal.Body>
                <Modal.Footer>
                    <Button variant="outline-danger" onClick={() => deleteCourse(true)}>Xác nhận</Button>
                    <Button variant="outline-secondary" onClick={() => setModalDeleteShow(false)}>Không</Button>
                </Modal.Footer>
            </Modal>
        </div>
    );
};

export default Course;