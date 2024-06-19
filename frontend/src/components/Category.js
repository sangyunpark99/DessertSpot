import styled from "styled-components";

const CategoryContainer = styled.div`
  width: 22%;
  text-align: center;
  margin-bottom: 1rem;
`;

const CategoryImage = styled.img`
  width: 100px;
  height: 65px;
  object-fit: scale-down;
  background-color: transparent;
`

const CategoryLabel = styled.div`
  font-size: 0.8rem;
`

const Category = ({src, label}) => {
    return(
        <CategoryContainer>
            <CategoryImage src={src}/>
            <CategoryLabel>{label}</CategoryLabel>
        </CategoryContainer>
    );
}

export default Category;