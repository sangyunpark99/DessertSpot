import styled from "styled-components";
import Product from "./Product";
import {useInfiniteQuery} from "react-query";
import {useCallback, useEffect, useRef} from "react";
import products from "../data/products";

const ProductsContainer = styled.div`
  padding-bottom: 60px;
  display: flex;
  flex-wrap: wrap;
  justify-content: space-around;
`;

const ITEMS_PER_PAGE = 2;

const fetchProducts = async ({ pageParam = 1 }) => {
    const startIndex = (pageParam - 1) * ITEMS_PER_PAGE;
    const endIndex = startIndex + ITEMS_PER_PAGE;
    const paginatedProducts = products.slice(startIndex, endIndex);

    return { data: paginatedProducts, nextPage: paginatedProducts.length ? pageParam + 1 : null };
};

// const fetchProducts = async ({pageParam = 1}) => {
//     const response = await fetch(``);
//     const data = await response.json();
//     return { data: data.products, nextPage: pageParam + 1};
// }

const Products = () => {

    const {
        data, // 불러온 데이터
        fetchNextPage, // 다음 페이지 불러 오는 함수
        hasNextPage, // 다음 페이지 존재 여부 확인
        isFetchingNextPage,
    } = useInfiniteQuery('products', fetchProducts, {
        getNextPageParam: (lastPage) => lastPage.nextPage ?? false, // 다음 페이지 파라미터 설정
    });

    const observerElem = useRef(null); // 관찰할 요소 참조

    const handleObserver = useCallback((entries) => { // 의존성 배열에 포함된 값이 변경될 때마다 새로운 함수
        console.log("handleObserver 호출");
        const [entry] = entries;
        if(entry.isIntersecting && hasNextPage) {
            fetchNextPage();
        }
    },[fetchNextPage, hasNextPage]);

    useEffect(() => {
        const observer = new IntersectionObserver(handleObserver, {
            root:null,
            rootMargin: '0px',// 관찰 대상이 뷰포트 경계에 도달할 때 콜백 실행
            threshold: 1.0,// 관찰 대상이 100% 뷰포트에 보일 때 콜백 실행
        });

        if(observerElem.current) observer.observe(observerElem.current);

        // 클린업 함수로 관찰을 멈춘다.
        return () => {
            if (observerElem.current) observer.unobserve(observerElem.current);
        }
    }, [handleObserver]);

    return (
        <ProductsContainer>
            {data?.pages.map((page, pageIndex) => (
                page.data.map((product, productIndex) => (
                    <Product key={`${pageIndex}-${productIndex}`} src={product.imageUrl} label={product.label} />
                ))
            ))}
            <div ref={observerElem}>
                {isFetchingNextPage ? 'Loading more...' : ''}
            </div>
        </ProductsContainer>
    );
}

export default Products;