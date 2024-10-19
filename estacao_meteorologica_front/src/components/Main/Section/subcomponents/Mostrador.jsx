import PropTypes from 'prop-types';

const Mostrador = ({ dados, unidade }) => {
  return (
    <div className="border-2 border-black rounded-2xl p-4 text-left flex gap-3 items-center w-full">
      <span className="text-base">{dados.texto}:</span>
      <span className="text-2xl font-bold">
        {dados.valor.toFixed(2)}
        {unidade}
      </span>
    </div>
  );
};

Mostrador.propTypes = {
  dados: PropTypes.object.isRequired,
  unidade: PropTypes.string.isRequired
};

export default Mostrador;
