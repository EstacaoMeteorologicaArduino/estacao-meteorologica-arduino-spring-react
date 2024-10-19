import PropTypes from 'prop-types';
import { useContext, useState } from 'react';
import Context from '../../context/Context';

import Subheader from './Subheader';

import CalendarioSVG from './../../assets/icons/calendar.svg';
import ClimaSVG from './../../assets/icons/cloud-sun.svg';
import LocalSVG from './../../assets/icons/map-marker.svg';
import AltitudeSVG from './../../assets/icons/mountain.svg';

const Header = ({ handleMudaData, dataDisplay, getDataAtual }) => {
  const altitude = useContext(Context)?.altitude;

  const [dataSelecionada, setDataSelecionada] = useState(dataDisplay);

  const handleAtualizaValorData = (e) => {
    const { value } = e.target;
    setDataSelecionada(value);
  };

  const handleSubmitDataSeletor = (e) => {
    e.preventDefault();
    const form = e.target;
    const dadosForm = new FormData(form);
    handleMudaData(dadosForm.get('data'));
  };

  const getQuinzeDiasAtras = () => {
    const dataBruta = new Date();
    dataBruta.setDate(dataBruta.getDate() - 15);
    const ano = dataBruta.getFullYear();
    const mes = String(dataBruta.getMonth() + 1).padStart(2, '0');
    const dia = String(dataBruta.getDate()).padStart(2, '0');

    return `${ano}-${mes}-${dia}`;
  };

  return (
    <header>
      <div className="border-2 border-black	rounded-2xl p-9 flex justify-center gap-3">
        <h1 className="text-[40px] max-lg:text-[24px] font-bold self-center">DADOS METEOROLÓGICOS</h1>
        <img
          src={ClimaSVG}
          className="w-[70px]"
        />
      </div>

      <Subheader.Root>
        <Subheader.Item
          iconSrc={CalendarioSVG}
          textContent={
            <form
              onSubmit={handleSubmitDataSeletor}
              className="flex w-full gap-3 max-lg:gap-1"
            >
              <input
                type="date"
                name="data"
                max={getDataAtual()}
                min={getQuinzeDiasAtras()}
                className="text-xl border border-black rounded-lg p-1 "
                value={dataSelecionada}
                onChange={handleAtualizaValorData}
              />
              <input
                type="submit"
                value={'Selecionar'}
                className="border-2 border-black rounded-md px-2 max-sm:px-1 bg-gray-900 text-white hover:text-black hover:bg-white transition-colors cursor-pointer"
              />
            </form>
          }
        />
        <Subheader.Item
          iconSrc={LocalSVG}
          textContent={<>Monte Carmelo - Minas Gerais - Brasil</>}
        />
        <Subheader.Item
          iconSrc={AltitudeSVG}
          textContent={
            <>
              Altitude: <span className="text-2xl ml-3">{altitude ? altitude.toFixed(2) + 'm':""}</span>
            </>
          }
        />
      </Subheader.Root>
    </header>
  );
};

Header.propTypes = {
  handleMudaData: PropTypes.func.isRequired,
  dataDisplay: PropTypes.string.isRequired
};

export default Header;
