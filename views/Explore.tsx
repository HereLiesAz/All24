
import React, { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';
import { Search, X } from 'lucide-react';
import { LOCATIONS } from '../constants';
import { Arcana } from '../types';
import { LocationCard } from '../components/LocationCard';

export const Explore: React.FC = () => {
  const { arcana } = useParams<{ arcana: string }>();
  const [selectedArcana, setSelectedArcana] = useState<Arcana | 'ALL'>('ALL');
  const [searchQuery, setSearchQuery] = useState('');

  useEffect(() => {
    if (arcana) {
      // Decode URL component to match Enum value
      const decoded = decodeURIComponent(arcana) as Arcana;
      if (Object.values(Arcana).includes(decoded)) {
        setSelectedArcana(decoded);
      } else {
        setSelectedArcana('ALL');
      }
    } else {
        setSelectedArcana('ALL');
    }
  }, [arcana]);

  const filteredLocations = LOCATIONS.filter(l => {
    const matchesArcana = selectedArcana === 'ALL' || l.arcana === selectedArcana;
    const searchLower = searchQuery.toLowerCase();
    const matchesSearch = searchQuery === '' ||
      l.name.toLowerCase().includes(searchLower) ||
      l.description.toLowerCase().includes(searchLower) ||
      l.address.toLowerCase().includes(searchLower) ||
      l.vibe.toLowerCase().includes(searchLower);
    return matchesArcana && matchesSearch;
  });

  return (
    <div className="py-8 space-y-12 animate-in fade-in duration-700">
      <div className="sticky top-0 bg-[var(--bg-color)]/95 backdrop-blur-md z-30 py-6 -mx-4 px-4 border-b border-[var(--accent-c)]/30 transition-colors landscape:static landscape:border-none landscape:bg-transparent landscape:p-0">
        <div className="flex flex-col md:flex-row md:items-center justify-between mb-4 gap-4">
          <h2 className="font-glitch text-2xl text-[var(--accent-c)] uppercase tracking-widest text-center landscape:text-left">
              {selectedArcana === 'ALL' ? 'FULL DECK' : selectedArcana}
          </h2>

          <div className="relative group max-w-sm w-full mx-auto md:mx-0">
            <Search className="absolute left-3 top-1/2 -translate-y-1/2 text-[var(--accent-c)] opacity-50 group-focus-within:opacity-100 transition-opacity" size={18} />
            <input
              type="text"
              value={searchQuery}
              onChange={(e) => setSearchQuery(e.target.value)}
              placeholder="SEARCH THE GLITCH..."
              className="w-full bg-[var(--input-bg)] border border-[var(--accent-c)]/30 focus:border-[var(--accent-c)] py-2 pl-10 pr-10 text-xs font-mono tracking-widest focus:outline-none transition-all placeholder:text-[var(--accent-c)]/30"
            />
            {searchQuery && (
              <button
                onClick={() => setSearchQuery('')}
                className="absolute right-3 top-1/2 -translate-y-1/2 text-[var(--accent-p)] hover:text-white transition-colors"
              >
                <X size={18} />
              </button>
            )}
          </div>
        </div>

        {/* Scrollable Filter Bar - still useful even if deep linked */}
        <div className="flex overflow-x-auto gap-4 pb-2 no-scrollbar landscape:flex-wrap">
          <button
            onClick={() => setSelectedArcana('ALL')}
            className={`whitespace-nowrap px-4 py-2 text-[12px] font-mono uppercase border transition-all ${selectedArcana === 'ALL' ? 'bg-[var(--accent-c)] text-black border-[var(--accent-c)] font-bold' : 'border-white/20 text-white/60 hover:text-white'}`}
          >
            All Cards
          </button>
          {Object.values(Arcana).map((a) => (
            <button
              key={a}
              onClick={() => setSelectedArcana(a)}
              className={`whitespace-nowrap px-4 py-2 text-[12px] font-mono uppercase border transition-all ${selectedArcana === a ? 'bg-[var(--accent-p)] text-white border-[var(--accent-p)] font-bold' : 'border-white/20 text-white/60 hover:text-white'}`}
            >
              {a}
            </button>
          ))}
        </div>
      </div>

      <div className="grid grid-cols-1 gap-20 landscape:grid-cols-2 lg:grid-cols-3 landscape:gap-8">
        {filteredLocations.length > 0 ? (
            filteredLocations.map(location => (
            <div key={location.id} className="space-y-4">
                <LocationCard location={location} />
            </div>
            ))
        ) : (
            <div className="col-span-full text-center py-20 animate-pulse">
                <p className="font-mono text-[var(--accent-p)] text-lg mb-2 uppercase">No Spectral Matches Found</p>
                <p className="font-mono text-white/40 text-xs uppercase tracking-[0.3em] mb-8">
                  {searchQuery ? `The glitch consumes all traces of "${searchQuery}"` : "This suit remains empty in the current timeline."}
                </p>
                {searchQuery && (
                  <button
                    onClick={() => setSearchQuery('')}
                    className="px-6 py-2 border border-[var(--accent-c)] text-[var(--accent-c)] font-mono text-[10px] uppercase tracking-widest hover:bg-[var(--accent-c)] hover:text-black transition-all"
                  >
                    Reset Connection
                  </button>
                )}
            </div>
        )}
      </div>
    </div>
  );
};
