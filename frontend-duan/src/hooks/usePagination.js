import React from 'react';
import Pagination from 'react-bootstrap/Pagination';

export default function UsePagination(props) {
    const { number, totalPages, setPage } = props
    let pagesToShow = []
    // Tính toán các trang cần hiển thị
    number > 1 && number < totalPages ? pagesToShow = [number] : pagesToShow = []
    let left = number - 1;
    let right = number + 1;
    let count = 3;

    // Hiển thị tối đa 5 trang. nếu tổng số trang nhiều hơn 7 thì sẽ hiển thị dấu '...' 
    // VD: 1 2 3 4 5 ... 7 || 1 ... 3 4 5 6 7 ... 10
    while (totalPages >= 7 && pagesToShow.length < 5) {
        if (left > 1) {
            pagesToShow.unshift(left);
            left--;
        }
        if (right < totalPages) {
            pagesToShow.push(right);
            right++;
        }
    }

    // nếu tổng số trang lớn hơn 3 và nhỏ hơn 7
    // VD: 1 2 3 4
    while (totalPages >= 3 && totalPages < 7 && count <= totalPages) {
        if (left > 1) {
            pagesToShow.unshift(left);
            left--;
        }
        if (right < totalPages) {
            pagesToShow.push(right);
            right++;
        }
        count++;
    }

    // click vào 1 trang bất kỳ
    const handleClick = (page) => {
        if (page == number - 1) return; // nếu trang click trùng với trang đang xem -> true
        if (page < 0 || page >= totalPages) return;  // nếu trang click lớn hơn trang cuối của data, hoặc bé hơn 0 -> true
        setPage(page)
    }

    return (
        <>
            {totalPages >= 2 &&
                <Pagination>
                    {/* <Pagination.First onClick={() => handleClick(0)} /> */}
                    <Pagination.Prev onClick={() => handleClick(number - 2)} />
                    <Pagination.Item onClick={() => handleClick(0)} active={number === 1}>{1}</Pagination.Item>
                    {totalPages > 7 && number > 4 && <Pagination.Ellipsis />}
                    {pagesToShow.map(page => (
                        <Pagination.Item key={page} onClick={() => { handleClick(page - 1) }} active={page === number}>{page}</Pagination.Item>
                    ))}
                    {totalPages > 7 && number < totalPages - 3 && <Pagination.Ellipsis />}
                    <Pagination.Item onClick={() => handleClick(totalPages - 1)} active={number === totalPages}>{totalPages}</Pagination.Item>
                    <Pagination.Next onClick={() => handleClick(number)} />
                    {/* <Pagination.Last onClick={() => handleClick(totalPages - 1)} /> */}
                </Pagination>
            }
        </>
    );
}
