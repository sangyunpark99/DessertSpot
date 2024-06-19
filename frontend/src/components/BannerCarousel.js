import Slider from 'react-slick';
import "slick-carousel/slick/slick.css";
import "slick-carousel/slick/slick-theme.css";
import styled from "styled-components";
import BannerItem from "./BannerItem";

const SliderContainer = styled.div`
  width: 100%;
  height: 100%;
`;

const StyledSlider = styled(Slider) `
  height: 100%;
  
  .slick-list {
    height: 100%;
    object-fit: cover;
    display: flex;
    align-items: center;
  }
  .slick-track {
    display: flex;
    align-items: center;
  }
  .slick-prev {
    left: 6px;
    z-index: 999;
  }
  .slick-next {
    right: 6px;
    z-index: 999;
  }
`;

const BannerCarousel = () => {
    const settings = {
        dots: false,
        infinite: true,
        speed: 500,
        slidesToShow: 1,
        slidesToScroll: 1,
        arrows: true,
        height: 100
    }

    const images = [
        {src:"https://rimage.gnst.jp/livejapan.com/public/article/detail/a/00/03/a0003580/img/basic/a0003580_main.jpg", alt:"dessert"},
        {src:"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR5d9EW6EvcKbeIMGqYr9FelmkBNYA0y6S5Jg&s", alt:"dessert"},
    ]

    return (
        <SliderContainer>
            <StyledSlider {...settings}>
                {images.map((image, index) => (
                    <BannerItem
                        key={index}
                        imageSrc={image.src}
                        altText={image.alt}
                    />
                ))}
            </StyledSlider>
        </SliderContainer>
    );
}

export default BannerCarousel;